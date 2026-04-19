package com.lokixprime.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lokixprime.ui.components.ChatInputBar
import com.lokixprime.ui.components.MessageBubble
import com.lokixprime.ui.components.TopNavigationBar
import com.lokixprime.ui.components.sidebar.AppSidebar
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.theme.*
import com.lokixprime.viewmodel.ChatViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel()
) {
    val messages by viewModel.messages.collectAsState()
    val inputText by viewModel.inputText.collectAsState()
    val isSettingsOpen by viewModel.isSettingsOpen.collectAsState()
    val isAwakenedMode by viewModel.isAwakenedMode.collectAsState()

    val listState = rememberLazyListState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    // Scroll to bottom FAB logic
    val showScrollToBottom by remember {
        derivedStateOf {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex < messages.size - 1
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppSidebar(
                onCloseSidebar = { scope.launch { drawerState.close() } },
                onSettingsClick = {
                    scope.launch { drawerState.close() }
                    viewModel.toggleSettings(true)
                },
                onClearChatClick = {
                    scope.launch { drawerState.close() }
                    viewModel.clearChat()
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundDark)
        ) {
            // Awakened mode dynamic background
            if (isAwakenedMode) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    LokiCyan.copy(alpha = 0.05f),
                                    Color.Transparent
                                )
                            )
                        )
                )
            }

            Column(modifier = Modifier.fillMaxSize()) {
                TopNavigationBar(
                    onSettingsClick = { viewModel.toggleSettings(true) },
                    onMenuClick = { scope.launch { drawerState.open() } },
                    isAwakenedMode = isAwakenedMode,
                    onAvatarClick = { viewModel.toggleAwakenedMode() }
                )

                if (messages.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            // Infinite logo logic switching
                            if (isAwakenedMode) {
                                // Placeholder for God-Level logo
                                Text(
                                    text = "∞",
                                    color = LokiCyan,
                                    fontSize = 120.sp,
                                    fontWeight = FontWeight.Thin,
                                    modifier = Modifier.padding(bottom = 24.dp)
                                )
                            } else {
                                Text(
                                    text = "∞",
                                    color = Color.White.copy(alpha=0.8f),
                                    fontSize = 80.sp,
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier.padding(bottom = 24.dp)
                                )
                            }

                            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                            val alpha by infiniteTransition.animateFloat(
                                initialValue = 0.5f,
                                targetValue = 1f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(1000, easing = LinearEasing),
                                    repeatMode = RepeatMode.Reverse
                                ),
                                label = "alphaPulse"
                            )

                            Text(
                                text = if (isAwakenedMode) "SYSTEM AWAKENED. AWAITING INPUT." else "AWAITING COMMAND, COMMANDER.",
                                color = (if (isAwakenedMode) LokiCyan else Color.Gray).copy(alpha = if (isAwakenedMode) 1f else alpha),
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 4.sp,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 24.dp),
                                textAlign = TextAlign.Center,
                                style = androidx.compose.ui.text.TextStyle(
                                    shadow = if (isAwakenedMode) androidx.compose.ui.graphics.Shadow(
                                        color = LokiCyan.copy(alpha = 0.6f),
                                        blurRadius = 15f
                                    ) else null
                                )
                            )
                        }
                    }
                } else {
                    Box(modifier = Modifier.weight(1f)) {
                        LazyColumn(
                            state = listState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
                        ) {
                            items(messages) { message ->
                                MessageBubble(
                                    message = message,
                                    isAwakenedMode = isAwakenedMode
                                )
                            }
                        }

                        // Floating Scroll-to-Bottom Button
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = 16.dp, bottom = 16.dp)
                        ) {
                            androidx.compose.animation.AnimatedVisibility(
                                visible = showScrollToBottom,
                                enter = fadeIn() + slideInVertically(initialOffsetY = { 50 }),
                                exit = fadeOut() + slideOutVertically(targetOffsetY = { 50 })
                            ) {
                                    FloatingActionButton(
                                        onClick = {
                                            scope.launch {
                                                listState.animateScrollToItem(messages.size - 1)
                                            }
                                        },
                                        containerColor = LokiCyan.copy(alpha = 0.9f),
                                        contentColor = Color.White,
                                        shape = androidx.compose.foundation.shape.CircleShape,
                                        modifier = Modifier.size(48.dp)
                                    ) {
                                        Icon(
                                            imageVector = LokiIcons.ArrowDown,
                                            contentDescription = "Scroll to bottom"
                                        )
                                    }
                            }
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
    }

    if (isSettingsOpen) {
        SettingsScreen(
            isAwakenedMode = isAwakenedMode,
            onToggleAwakenedMode = { viewModel.toggleAwakenedMode() },
            onClose = { viewModel.toggleSettings(false) }
        )
    }
}
