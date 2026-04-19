package com.lokixprime.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lokixprime.data.db.AppDatabase
import com.lokixprime.data.db.entity.ChatSessionEntity
import com.lokixprime.data.db.entity.MessageEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import com.lokixprime.data.api.ApiClient
import com.lokixprime.data.api.ChatRequest
import com.lokixprime.data.api.MessageHistory
import com.lokixprime.data.api.MessagePart

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val chatDao = AppDatabase.getDatabase(application).chatDao()

    private val _sessions = MutableStateFlow<List<ChatSessionEntity>>(emptyList())
    val sessions: StateFlow<List<ChatSessionEntity>> = _sessions.asStateFlow()

    private val _currentSessionId = MutableStateFlow<String?>(null)
    val currentSessionId: StateFlow<String?> = _currentSessionId.asStateFlow()

    private val _messages = MutableStateFlow<List<MessageEntity>>(emptyList())
    val messages: StateFlow<List<MessageEntity>> = _messages.asStateFlow()

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    private val _isSettingsOpen = MutableStateFlow(false)
    val isSettingsOpen: StateFlow<Boolean> = _isSettingsOpen.asStateFlow()

    private val _isAwakenedMode = MutableStateFlow(false)
    val isAwakenedMode: StateFlow<Boolean> = _isAwakenedMode.asStateFlow()

    init {
        viewModelScope.launch {
            chatDao.getAllSessionsFlow().collectLatest { sessionList ->
                _sessions.value = sessionList
                if (_currentSessionId.value == null && sessionList.isNotEmpty()) {
                    _currentSessionId.value = sessionList.first().id
                }
            }
        }

        viewModelScope.launch {
            _currentSessionId.collectLatest { sessionId ->
                if (sessionId != null) {
                    chatDao.getMessagesForSessionFlow(sessionId).collectLatest { messageList ->
                        _messages.value = messageList
                    }
                } else {
                    _messages.value = emptyList()
                }
            }
        }
    }

    fun setCurrentSession(id: String) {
        _currentSessionId.value = id
    }

    fun createNewSession() {
        val newSessionId = UUID.randomUUID().toString()
        val newSession = ChatSessionEntity(
            id = newSessionId,
            title = "New Chat",
            updatedAt = System.currentTimeMillis()
        )
        viewModelScope.launch {
            chatDao.insertSession(newSession)
            _currentSessionId.value = newSessionId
        }
    }

    fun updateInputText(text: String) {
        _inputText.value = text
    }

    fun toggleSettings(isOpen: Boolean) {
        _isSettingsOpen.value = isOpen
    }

    fun toggleAwakenedMode() {
        _isAwakenedMode.value = !_isAwakenedMode.value
    }

    fun sendMessage() {
        val text = _inputText.value.trim()
        if (text.isEmpty()) return

        var sessionId = _currentSessionId.value
        viewModelScope.launch {
            if (sessionId == null) {
                sessionId = UUID.randomUUID().toString()
                val newSession = ChatSessionEntity(
                    id = sessionId!!,
                    title = text.take(30),
                    updatedAt = System.currentTimeMillis()
                )
                chatDao.insertSession(newSession)
                _currentSessionId.value = sessionId
            }

            val userMessage = MessageEntity(
                id = UUID.randomUUID().toString(),
                sessionId = sessionId!!,
                role = "user",
                content = text,
                timestamp = System.currentTimeMillis()
            )
            chatDao.insertMessage(userMessage)
            chatDao.updateSessionTitle(sessionId!!, text.take(30), System.currentTimeMillis())
            _inputText.value = ""

            try {
                // Prepare history
                val currentMessages = chatDao.getMessagesForSession(sessionId!!)
                val history = currentMessages.map {
                    MessageHistory(role = it.role, parts = listOf(MessagePart(it.content)))
                }

                val request = ChatRequest(
                    message = text,
                    history = history,
                    mode = "pro", // TODO: hook up to settings
                    systemInstruction = "You are LOKI PRIME", // TODO: hook up to settings
                    temperature = 0.7f
                )

                val response = ApiClient.apiService.generateChatResponse(request)

                val botMessage = MessageEntity(
                    id = UUID.randomUUID().toString(),
                    sessionId = sessionId!!,
                    role = "model",
                    content = response.text ?: "SYSTEM ERROR: Empty response",
                    reasoning = response.reasoning,
                    timestamp = System.currentTimeMillis()
                )
                chatDao.insertMessage(botMessage)
                chatDao.updateSessionTitle(sessionId!!, botMessage.content.take(30), System.currentTimeMillis())

            } catch (e: Exception) {
                val errorMsg = MessageEntity(
                    id = UUID.randomUUID().toString(),
                    sessionId = sessionId!!,
                    role = "model",
                    content = "SYSTEM ERROR: ${e.message}",
                    timestamp = System.currentTimeMillis()
                )
                chatDao.insertMessage(errorMsg)
            }
        }
    }

    fun deleteMessage(id: String) {
        val sessionId = _currentSessionId.value ?: return
        viewModelScope.launch {
            chatDao.deleteMessage(sessionId, id)
        }
    }

    fun clearChat() {
        val sessionId = _currentSessionId.value ?: return
        viewModelScope.launch {
            chatDao.deleteMessagesBySession(sessionId)
        }
    }
}
