package com.lokixprime.ui.components.sidebar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.data.db.entity.ChatSessionEntity
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.modifiers.glowPulse
import com.lokixprime.ui.theme.Inter
import com.lokixprime.ui.theme.LokiCyan
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimelineItem(
    session: ChatSessionEntity,
    isActive: Boolean,
    isAwakened: Boolean,
    effectSidebar: Boolean,
    onClick: (String) -> Unit,
    onDelete: (String) -> Unit,
    onPin: (String) -> Unit,
    onRename: (String, String) -> Unit,
    index: Int
) {
    val isDark = isSystemInDarkTheme()
    var isMenuOpen by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var editTitle by remember { mutableStateOf(session.title) }
    val focusRequester = remember { FocusRequester() }

    val showAwakenedStyle = isAwakened || effectSidebar

    val containerBgColor = when {
        isActive -> if (showAwakenedStyle) Color(0xFF00F2FF).copy(alpha = 0.2f) else if (isDark) Color.White.copy(alpha = 0.1f) else Color.White
        else -> Color.Transparent
    }

    val containerBorderColor = when {
        isActive -> if (showAwakenedStyle) Color(0xFF00F2FF).copy(alpha = 0.4f) else if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFF00F2FF).copy(alpha = 0.5f)
        else -> Color.Transparent
    }

    val textColor = when {
        isActive -> if (showAwakenedStyle) {
            if (isDark) Color.White else Color(0xFF1E293B) // slate-800
        } else {
            if (isDark) Color.White else Color(0xFF0E7490) // cyan-700
        }
        else -> if (showAwakenedStyle) {
            if (isDark) Color(0xFFCBD5E1) else Color(0xFF334155) // slate-300 / slate-700
        } else {
            if (isDark) Color(0xFFCBD5E1) else Color(0xFF475569) // slate-300 / slate-600
        }
    }

    val iconTint = when {
        session.isPinned -> Color(0xFFF59E0B) // amber-500
        isActive -> if (isDark) Color(0xFF00F2FF) else Color(0xFF0891B2) // cyan-600
        else -> if (showAwakenedStyle) {
            if (isDark) Color(0xFF94A3B8) else Color(0xFF64748B) // slate-400 / slate-500
        } else {
            if (isDark) Color(0xFF6B6B80) else Color(0xFF94A3B8) // custom / slate-400
        }
    }

    LaunchedEffect(isEditing) {
        if (isEditing) {
            delay(100)
            focusRequester.requestFocus()
        }
    }

    val submitRename = {
        if (isEditing) {
            if (editTitle.isNotBlank() && editTitle != session.title) {
                onRename(session.id, editTitle.trim())
            } else {
                editTitle = session.title
            }
            isEditing = false
            isMenuOpen = false
        }
    }

    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(index * 30L)
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            initialOffsetX = { -50 },
            animationSpec = tween(400)
        ) + fadeIn(animationSpec = tween(400)),
        exit = slideOutHorizontally(
            targetOffsetX = { -50 },
            animationSpec = tween(400)
        ) + fadeOut(animationSpec = tween(400))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(containerBgColor)
                    .border(1.dp, containerBorderColor, RoundedCornerShape(8.dp))
                    .combinedClickable(
                        onClick = {
                            if (!isEditing && !isMenuOpen) {
                                onClick(session.id)
                            }
                        },
                        onLongClick = {
                            if (!isEditing) {
                                isMenuOpen = true
                            }
                        }
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Active indicator line
                if (isActive) {
                    Box(
                        modifier = Modifier
                            .absoluteOffset(x = (-16).dp)
                            .width(4.dp)
                            .height(24.dp)
                            .clip(RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
                            .background(Color(0xFF00F2FF))
                            .glowPulse(color = Color(0xFF00F2FF))
                    )
                }

                Icon(
                    imageVector = if (session.isPinned) LokiIcons.Pin else LokiIcons.MessageSquare,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                if (isEditing) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicTextField(
                            value = editTitle,
                            onValueChange = { editTitle = it },
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester)
                                .background(if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.1f))
                                .padding(horizontal = 4.dp, vertical = 2.dp),
                            textStyle = TextStyle(
                                color = textColor,
                                fontSize = 14.sp,
                                fontFamily = Inter
                            ),
                            cursorBrush = SolidColor(LokiCyan),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = { submitRename() }),
                            singleLine = true
                        )
                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(LokiCyan.copy(alpha = 0.5f))
                        )
                        IconButton(
                            onClick = submitRename,
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = LokiIcons.Check,
                                contentDescription = "Confirm",
                                tint = LokiCyan,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                } else {
                    Text(
                        text = session.title,
                        color = textColor,
                        fontSize = 14.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (!isEditing) {
                    IconButton(
                        onClick = { isMenuOpen = true },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = LokiIcons.MoreVertical,
                            contentDescription = "More",
                            tint = if (isDark) Color(0xFF6B6B80) else Color(0xFF94A3B8),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Dropdown Menu
            DropdownMenu(
                expanded = isMenuOpen,
                onDismissRequest = { isMenuOpen = false },
                offset = DpOffset(x = 200.dp, y = 0.dp),
                modifier = Modifier
                    .background(
                        if (showAwakenedStyle) Color.Black.copy(alpha = 0.8f) else if (isDark) Color(0xFF1a1b26).copy(alpha = 0.95f) else Color.White.copy(alpha = 0.95f)
                    )
                    .border(
                        1.dp,
                        if (showAwakenedStyle) Color.White.copy(alpha = 0.1f) else if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0),
                        RoundedCornerShape(12.dp)
                    )
            ) {
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(LokiIcons.Edit2, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Rename", fontSize = 12.sp, fontFamily = Inter)
                        }
                    },
                    onClick = {
                        isMenuOpen = false
                        isEditing = true
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = if (showAwakenedStyle) Color(0xFFCBD5E1) else if (isDark) Color(0xFFCBD5E1) else Color(0xFF475569),
                        leadingIconColor = if (showAwakenedStyle) Color(0xFFCBD5E1) else if (isDark) Color(0xFFCBD5E1) else Color(0xFF475569)
                    )
                )
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(if (session.isPinned) LokiIcons.PinOff else LokiIcons.Pin, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(if (session.isPinned) "Unpin" else "Pin", fontSize = 12.sp, fontFamily = Inter)
                        }
                    },
                    onClick = {
                        onPin(session.id)
                        isMenuOpen = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = if (showAwakenedStyle) Color(0xFFCBD5E1) else if (isDark) Color(0xFFCBD5E1) else Color(0xFF475569),
                        leadingIconColor = if (showAwakenedStyle) Color(0xFFCBD5E1) else if (isDark) Color(0xFFCBD5E1) else Color(0xFF475569)
                    )
                )
                HorizontalDivider(color = if (showAwakenedStyle || isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0))
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(LokiIcons.Trash2, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Delete", fontSize = 12.sp, fontFamily = Inter)
                        }
                    },
                    onClick = {
                        onDelete(session.id)
                        isMenuOpen = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = Color(0xFFF43F5E), // rose-500
                        leadingIconColor = Color(0xFFF43F5E)
                    )
                )
            }
        }
    }
}
