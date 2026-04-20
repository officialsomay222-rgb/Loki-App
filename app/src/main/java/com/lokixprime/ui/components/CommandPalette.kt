package com.lokixprime.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.viewmodel.ChatViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommandPalette(
    isOpen: Boolean,
    onClose: () -> Unit,
    viewModel: ChatViewModel = viewModel()
) {
    val isAwakenedMode by viewModel.isAwakenedMode.collectAsState()
    val isDark = isSystemInDarkTheme() || isAwakenedMode
    var query by remember { mutableStateOf("") }
    val sessions by viewModel.sessions.collectAsState()

    val filteredSessions = if (query.isEmpty()) {
        sessions
    } else {
        sessions.filter { it.title.contains(query, ignoreCase = true) }
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isOpen) {
        if (isOpen) {
            query = ""
            // Delay slightly to ensure visibility before requesting focus
            delay(100)
            try {
                focusRequester.requestFocus()
            } catch (e: Exception) {
                // Ignore focus errors
            }
        }
    }

    // Backdrop
    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn(tween(200)),
        exit = fadeOut(tween(200)),
        modifier = Modifier.zIndex(100f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                    indication = null,
                    onClick = onClose
                )
        )
    }

    // Modal Content
    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn(tween(300)) + slideInVertically(
            initialOffsetY = { -it / 10 },
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessMediumLow)
        ) + scaleIn(initialScale = 0.95f),
        exit = fadeOut(tween(200)) + slideOutVertically(
            targetOffsetY = { -it / 10 },
            animationSpec = tween(200)
        ) + scaleOut(targetScale = 0.95f),
        modifier = Modifier.zIndex(101f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 64.dp)
                .statusBarsPadding(),
            contentAlignment = Alignment.TopCenter
        ) {
            val bgColor = if (isDark) Color(0xFF1E293B).copy(alpha = 0.8f) else Color.White.copy(alpha = 0.9f)
            val textColor = if (isDark) Color.White else Color(0xFF0F172A)
            val placeholderColor = if (isDark) Color(0xFF94A3B8) else Color(0xFF64748B)
            val borderColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0)
            val hoverBgColor = if (isDark) Color.White.copy(alpha = 0.05f) else Color(0xFFF1F5F9)

            Column(
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 24.dp,
                        shape = RoundedCornerShape(12.dp),
                        spotColor = if (isDark) Color.Black else Color.Gray.copy(alpha = 0.5f)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(bgColor)
            ) {
                // Search Input Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Icon(
                        imageVector = LokiIcons.Search,
                        contentDescription = "Search",
                        tint = placeholderColor,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    BasicTextField(
                        value = query,
                        onValueChange = { query = it },
                        textStyle = TextStyle(
                            color = textColor,
                            fontSize = 16.sp
                        ),
                        cursorBrush = SolidColor(if (isDark) Color(0xFF00F2FF) else Color.Black),
                        singleLine = true,
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester),
                        decorationBox = { innerTextField ->
                            if (query.isEmpty()) {
                                Text(
                                    text = "Search sessions or commands...",
                                    color = placeholderColor,
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Transparent)
                    ) {
                        Icon(
                            imageVector = LokiIcons.X,
                            contentDescription = "Close",
                            tint = placeholderColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                HorizontalDivider(color = borderColor)

                // Content List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 320.dp)
                        .padding(8.dp)
                ) {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    viewModel.createNewSession()
                                    onClose()
                                }
                                .padding(12.dp)
                        ) {
                            Icon(
                                imageVector = LokiIcons.Plus,
                                contentDescription = "New",
                                tint = textColor,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "New Awakening",
                                color = textColor,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    items(filteredSessions) { session ->
                        Text(
                            text = session.title,
                            color = if (isDark) Color(0xFFCBD5E1) else Color(0xFF334155),
                            fontSize = 15.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable {
                                    viewModel.setCurrentSession(session.id)
                                    onClose()
                                }
                                .padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}
