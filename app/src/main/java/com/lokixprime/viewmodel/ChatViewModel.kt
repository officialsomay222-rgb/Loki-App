package com.lokixprime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    private val _isSettingsOpen = MutableStateFlow(false)
    val isSettingsOpen: StateFlow<Boolean> = _isSettingsOpen.asStateFlow()

    private val _isAwakenedMode = MutableStateFlow(false)
    val isAwakenedMode: StateFlow<Boolean> = _isAwakenedMode.asStateFlow()

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

        val userMessage = ChatMessage(content = text, isUser = true)
        _messages.value = _messages.value + userMessage
        _inputText.value = ""

        // Mock bot response
        viewModelScope.launch {
            // Add a "typing" delay
            delay(1000)

            val responseText = "You said: $text\n\n[This is a native Android placeholder response. The true API integration will be implemented later.]"
            val botMessage = ChatMessage(content = responseText, isUser = false)
            _messages.value = _messages.value + botMessage
        }
    }

    fun deleteMessage(id: String) {
        _messages.value = _messages.value.filter { it.id != id }
    }

    fun clearChat() {
        _messages.value = emptyList()
    }
}
