package com.lokixprime.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lokixprime.ui.components.ChatInputBar
import com.lokixprime.ui.components.MessageBubble
import com.lokixprime.ui.components.TopNavigationBar
import com.lokixprime.viewmodel.ChatViewModel
import com.lokixprime.ui.theme.LokiCyan

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel()
) {
    val messages by viewModel.messages.collectAsState()
    val inputText by viewModel.inputText.collectAsState()
    val isSettingsOpen by viewModel.isSettingsOpen.collectAsState()
    val isAwakenedMode by viewModel.isAwakenedMode.collectAsState()

    val listState = rememberLazyListState()

    // Scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Optional Background Effect based on Awakened mode
        if (isAwakenedMode) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LokiCyan.copy(alpha = 0.03f))
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {

            TopNavigationBar(
                onSettingsClick = { viewModel.toggleSettings(true) },
                onMenuClick = { /* TODO */ },
                isAwakenedMode = isAwakenedMode,
                onAvatarClick = { viewModel.toggleAwakenedMode() }
            )

            // Chat Messages Area
            if (messages.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = if (isAwakenedMode) "SYSTEM AWAKENED" else "AWAITING COMMAND",
                            color = if (isAwakenedMode) LokiCyan else Color.Gray,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp,
                            fontSize = 12.sp
                        )
                    }
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(messages) { message ->
                        MessageBubble(message = message)
                    }
                }
            }

            ChatInputBar(
                text = inputText,
                onTextChange = { viewModel.updateInputText(it) },
                onSendClick = { viewModel.sendMessage() },
                isAwakenedMode = isAwakenedMode
            )
        }
    }

    if (isSettingsOpen) {
        SettingsScreen(
            isAwakenedMode = isAwakenedMode,
            onToggleAwakenedMode = { viewModel.toggleAwakenedMode() },
            onClose = { viewModel.toggleSettings(false) }
        )
    }
}
