package com.lokixprime.ui.components.sidebar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.lokixprime.data.db.entity.ChatSessionEntity
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.theme.Inter

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
    var isMenuOpen by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var editTitle by remember { mutableStateOf(session.title) }

    val isDark = isSystemInDarkTheme()
    val isAwakenedMode = isAwakened || effectSidebar

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isEditing) {
        if (isEditing) {
            editTitle = session.title
            focusRequester.requestFocus()
        }
    }

    val submitRename = {
        if (isEditing) {
            val trimmed = editTitle.trim()
            if (trimmed.isNotEmpty() && trimmed != session.title) {
                onRename(session.id, trimmed)
            } else {
                editTitle = session.title
            }
            isEditing = false
            isMenuOpen = false
            focusManager.clearFocus()
        }
    }

    // Styles based on states
    val containerBg = if (isActive) {
        if (isAwakenedMode) Color(0xFF00F2FF).copy(alpha = 0.2f)
        else if (isDark) Color.White.copy(alpha = 0.1f) else Color.White
    } else {
        if (isHovered) {
            if (isDark) Color.White.copy(alpha = 0.05f) else Color.White.copy(alpha = 0.5f)
        } else {
            Color.Transparent
        }
    }

    val textColor = if (isActive) {
        if (isAwakenedMode) if (isDark) Color.White else Color(0xFF1E293B)
        else if (isDark) Color.White else Color(0xFF0E7490) // text-cyan-700
    } else {
        if (isAwakenedMode) {
            if (isHovered) if (isDark) Color.White else Color(0xFF0F172A)
            else if (isDark) Color(0xFFCBD5E1) else Color(0xFF334155) // slate-300 / slate-700
        } else {
            if (isHovered) if (isDark) Color.White else Color(0xFF0F172A)
            else if (isDark) Color(0xFFCBD5E1) else Color(0xFF475569) // slate-300 / slate-600
        }
    }

    val borderColor = if (isActive) {
        if (isAwakenedMode) Color(0xFF00F2FF).copy(alpha = 0.4f)
        else if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFF00F2FF).copy(alpha = 0.5f) // cyan-200/50 approx
    } else {
        Color.Transparent
    }

    val shadowModifier = if (isActive) {
        if (isAwakenedMode) Modifier.shadow(15.dp, spotColor = Color(0xFF00F2FF).copy(alpha = 0.15f), ambientColor = Color(0xFF00F2FF).copy(alpha = 0.15f))
        else Modifier.shadow(4.dp) // shadow-md
    } else Modifier

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .then(shadowModifier)
            .background(containerBg, RoundedCornerShape(8.dp))
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .hoverable(interactionSource)
            .pointerInput(session.id, isEditing, isMenuOpen, onClick) {
                detectTapGestures(
                    onTap = {
                        if (!isEditing && !isMenuOpen) onClick(session.id)
                    },
                    onLongPress = {
                        isMenuOpen = true
                    }
                )
            }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        if (isActive) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = (-16).dp)
                    .width(4.dp)
                    .height(24.dp)
                    .background(
                        color = Color(0xFF00F2FF),
                        shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
                    )
                    .drawBehind {
                        drawRect(
                            color = Color(0xFF00F2FF),
                            alpha = 1f
                        )
                    }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (session.isPinned) {
                    Icon(
                        imageVector = LokiIcons.Pin,
                        contentDescription = "Pinned",
                        tint = if (isDark) Color(0xFFFBBF24) else Color(0xFFF59E0B), // amber-400 : amber-500
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    val iconTint = if (isActive) {
                        if (isDark) Color(0xFF00F2FF) else Color(0xFF0891B2) // text-[#00f2ff] : cyan-600
                    } else if (isAwakenedMode) {
                        if (isHovered) if (isDark) Color(0xFF22D3EE) else Color(0xFF0891B2)
                        else if (isDark) Color(0xFF94A3B8) else Color(0xFF64748B) // slate-400 : slate-500
                    } else {
                        if (isHovered) Color(0xFF06B6D4) // cyan-500
                        else if (isDark) Color(0xFF6B6B80) else Color(0xFF94A3B8)
                    }
                    Icon(
                        imageVector = LokiIcons.MessageSquare,
                        contentDescription = "Message",
                        tint = iconTint,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                if (isEditing) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicTextField(
                            value = editTitle,
                            onValueChange = { editTitle = it },
                            textStyle = TextStyle(
                                color = textColor,
                                fontSize = 14.sp,
                                fontFamily = Inter
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = { submitRename() }),
                            singleLine = true,
                            cursorBrush = SolidColor(Color(0xFF00F2FF)),
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester)
                                .background(if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.1f))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(
                            onClick = submitRename,
                            modifier = Modifier
                                .size(24.dp)
                                .background(if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                        ) {
                            Icon(
                                imageVector = LokiIcons.Check,
                                contentDescription = "Confirm",
                                tint = Color(0xFF06B6D4), // cyan-500
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                } else {
                    Text(
                        text = session.title,
                        color = textColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Inter,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            if (!isEditing) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Mobile More Menu
                    Box {
                        IconButton(
                            onClick = { isMenuOpen = true },
                            modifier = Modifier
                                .size(28.dp)
                                .background(
                                    if (isMenuOpen) {
                                        if (isDark) Color.Black.copy(alpha = 0.5f) else Color(0xFFE2E8F0)
                                    } else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                )
                        ) {
                            val moreTint = if (isAwakenedMode) {
                                if (isDark) Color(0xFF94A3B8) else Color(0xFF64748B)
                            } else {
                                if (isDark) Color(0xFF6B6B80) else Color(0xFF94A3B8)
                            }
                            Icon(
                                imageVector = LokiIcons.MoreVertical,
                                contentDescription = "More",
                                tint = moreTint,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = isMenuOpen,
                            onDismissRequest = { isMenuOpen = false },
                            modifier = Modifier
                                .background(
                                    if (isAwakenedMode) Color.Black.copy(alpha = 0.8f)
                                    else if (isDark) Color(0xFF1A1B26).copy(alpha = 0.95f) else Color.White.copy(alpha = 0.95f)
                                )
                                .border(
                                    1.dp,
                                    if (isAwakenedMode) Color.White.copy(alpha = 0.1f)
                                    else if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0),
                                    RoundedCornerShape(12.dp)
                                )
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            val menuItemTextColor = if (isAwakenedMode) {
                                Color(0xFFCBD5E1) // slate-300
                            } else {
                                if (isDark) Color(0xFFCBD5E1) else Color(0xFF475569) // slate-300 : slate-600
                            }

                            DropdownMenuItem(
                                text = { Text("Rename", color = menuItemTextColor, fontSize = 12.sp, fontWeight = FontWeight.Medium) },
                                leadingIcon = { Icon(LokiIcons.Edit2, "Rename", tint = menuItemTextColor, modifier = Modifier.size(14.dp)) },
                                onClick = {
                                    isMenuOpen = false
                                    isEditing = true
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(if (session.isPinned) "Unpin" else "Pin", color = menuItemTextColor, fontSize = 12.sp, fontWeight = FontWeight.Medium) },
                                leadingIcon = {
                                    Icon(
                                        if (session.isPinned) LokiIcons.PinOff else LokiIcons.Pin,
                                        "Pin",
                                        tint = menuItemTextColor,
                                        modifier = Modifier.size(14.dp)
                                    )
                                },
                                onClick = {
                                    onPin(session.id)
                                    isMenuOpen = false
                                }
                            )
                            HorizontalDivider(color = if (isAwakenedMode) Color.White.copy(alpha = 0.1f) else if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0))
                            DropdownMenuItem(
                                text = { Text("Delete", color = Color(0xFFF43F5E), fontSize = 12.sp, fontWeight = FontWeight.Medium) },
                                leadingIcon = { Icon(LokiIcons.Trash2, "Delete", tint = Color(0xFFF43F5E), modifier = Modifier.size(14.dp)) },
                                onClick = {
                                    onDelete(session.id)
                                    isMenuOpen = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
