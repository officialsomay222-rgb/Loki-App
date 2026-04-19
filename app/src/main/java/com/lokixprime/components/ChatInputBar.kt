package com.lokixprime.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatInputBar(
    modifier: Modifier = Modifier,
    isAwakened: Boolean = false,
    isRecording: Boolean = false,
    isLoading: Boolean = false,
    modelMode: String = "Fast",
    hasAttachments: Boolean = false, // Simplified for placeholder UI
    onAttachClicked: () -> Unit = {},
    onOptionsClicked: () -> Unit = {},
    onModelSelectorClicked: () -> Unit = {},
    onMicClicked: () -> Unit = {},
    onSendClicked: () -> Unit = {},
    onLiveClicked: () -> Unit = {},
    onStopGenerationClicked: () -> Unit = {},
    onRemoveAttachmentClicked: () -> Unit = {}
) {
    var inputText by remember { mutableStateOf("") }
    val isDarkTheme = isSystemInDarkTheme()

    // Theme Colors derived from tailwind slate/rose/white/black palette in web
    val textColor = if (isDarkTheme) Color(0xFFE3E3E3) else Color(0xFF0F172A)
    val placeholderColor = if (isDarkTheme) Color(0xFFC4C7C5) else Color(0xFF94A3B8)
    val iconColor = if (isDarkTheme) Color(0xFFC4C7C5) else Color(0xFF64748B)

    // Background colors
    val normalBgColor = if (isDarkTheme) Color(0x0DFFFFFF) else Color(0x33F1F5F9) // white/5 or slate-100/20
    val awakenedBgColor = if (isDarkTheme) Color(0xE6050505) else Color(0x99FFFFFF) // #050505/90 or white/60

    // Button colors
    val buttonHoverBg = if (isDarkTheme) Color(0x1AFFFFFF) else Color(0xFFE2E8F0) // white/10 or slate-200
    val buttonActiveBg = if (isDarkTheme) Color(0x33FFFFFF) else Color(0xFFCBD5E1) // white/20 or slate-300

    val containerColor by animateColorAsState(
        targetValue = if (isAwakened) awakenedBgColor else normalBgColor,
        animationSpec = tween(500), label = "containerColor"
    )

    // Awakened Animations
    val infiniteTransition = rememberInfiniteTransition(label = "awakenedAnimation")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotation"
    )

    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "pulse"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .let {
                if (isAwakened) {
                    it.padding(2.dp) // Space for the animated border
                } else {
                    it
                }
            }
    ) {
        // Awakened Background Effect (Spinning Conic Gradient & Pulse)
        if (isAwakened) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(32.dp))
                    .drawBehind {
                        // Equivalent to CSS conic-gradient
                        val brush = Brush.sweepGradient(
                            0.0f to Color.Transparent,
                            0.15f to Color(0x6600F2FF),
                            0.30f to Color(0xFF00F2FF),
                            0.30f to Color.Transparent,
                            0.50f to Color.Transparent,
                            0.65f to Color(0x66BD00FF),
                            0.80f to Color(0xFFBD00FF),
                            0.80f to Color.Transparent,
                            1.0f to Color.Transparent,
                            center = Offset(size.width / 2, size.height / 2)
                        )

                        rotate(rotation) {
                            // Draw an oversized circle to cover the rounded rectangle when spinning
                            drawCircle(
                                brush = brush,
                                radius = maxOf(size.width, size.height) * 1.5f,
                                center = Offset(size.width / 2, size.height / 2)
                            )
                        }

                        // Inner pulsing shadow approximation
                        drawRoundRect(
                            color = Color(0xFF00F2FF).copy(alpha = pulseAlpha * 0.5f),
                            size = size,
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(32.dp.toPx(), 32.dp.toPx()),
                            style = Stroke(width = 4.dp.toPx())
                        )
                    }
            )
        }

        // Main Input Surface
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .let {
                    if (isRecording) {
                        it.border(1.dp, Color.White.copy(alpha = 0.5f), RoundedCornerShape(30.dp))
                    } else {
                        it
                    }
                },
            color = containerColor,
            tonalElevation = if (isAwakened) 8.dp else 0.dp,
            shadowElevation = if (isAwakened) 8.dp else 0.dp
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                // Attachments Preview Placeholder
                if (hasAttachments) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Mock 1 attachment
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Gray.copy(alpha = 0.3f))
                                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Default.BrokenImage, // Placeholder for image
                                contentDescription = null,
                                tint = iconColor,
                                modifier = Modifier.align(Alignment.Center)
                            )

                            // Remove button
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(4.dp)
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(Color.Black.copy(alpha = 0.5f))
                                    .clickable { onRemoveAttachmentClicked() },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove",
                                    tint = Color.White,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }

                // Text Input
                BasicTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    textStyle = TextStyle(
                        color = if (isAwakened) Color.White else textColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp
                    ),
                    cursorBrush = SolidColor(if (isAwakened) Color(0xFF00F2FF) else textColor),
                    maxLines = 5,
                    decorationBox = { innerTextField ->
                        if (inputText.isEmpty() && !isRecording) {
                            Text(
                                text = "Ask AI...",
                                color = placeholderColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        } else if (isRecording) {
                            Text(
                                text = "Listening...",
                                color = placeholderColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Bottom Action Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left Actions (Attach, Options)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(
                            onClick = onAttachClicked,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Attach",
                                tint = iconColor
                            )
                        }

                        IconButton(
                            onClick = onOptionsClicked,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Options",
                                tint = iconColor
                            )
                        }
                    }

                    // Right Actions (Model Selector, Mic, Send/Live)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Model Selector (Mocked Dropdown trigger)
                        Surface(
                            shape = CircleShape,
                            color = Color.Transparent,
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
                            modifier = Modifier.clickable { onModelSelectorClicked() }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = modelMode,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = iconColor
                                )
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Select Model",
                                    tint = iconColor,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        // Mic / Stop Recording
                        Surface(
                            shape = CircleShape,
                            color = if (isRecording) Color(0x33F43F5E) else Color.Transparent, // rose-500/20
                            border = if (isRecording) androidx.compose.foundation.BorderStroke(1.dp, Color(0x80F43F5E))
                                     else androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
                            modifier = Modifier
                                .size(48.dp)
                                .clickable { onMicClicked() }
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                if (isRecording) {
                                    Icon(
                                        imageVector = Icons.Default.Stop,
                                        contentDescription = "Stop Recording",
                                        tint = Color(0xFFF43F5E), // rose-500
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Mic,
                                        contentDescription = "Voice Input",
                                        tint = iconColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }

                        // Send / Stop Generation / Live Connection
                        val canSend = inputText.isNotBlank() || hasAttachments

                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + scaleIn(),
                            exit = fadeOut() + scaleOut()
                        ) {
                            if (isLoading) {
                                Surface(
                                    shape = CircleShape,
                                    color = Color(0x33F43F5E), // rose-500/20
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x80FB7185)), // rose-400/50
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clickable { onStopGenerationClicked() }
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .border(2.dp, Color(0xFFFB7185), CircleShape)
                                                .background(Color(0x1AFB7185), CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Box(modifier = Modifier.size(8.dp).background(Color(0xFFFB7185), CircleShape))
                                        }
                                    }
                                }
                            } else if (canSend) {
                                Surface(
                                    shape = CircleShape,
                                    color = buttonHoverBg,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clickable { onSendClicked() }
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.Send,
                                            contentDescription = "Send Message",
                                            tint = if (isDarkTheme) Color(0xFFE3E3E3) else Color(0xFF0F172A),
                                            modifier = Modifier.size(20.dp).offset(x = 2.dp)
                                        )
                                    }
                                }
                            } else {
                                // Live Mode Button
                                Surface(
                                    shape = CircleShape,
                                    color = buttonHoverBg,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clickable { onLiveClicked() }
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.Call, // Placeholder for specific live icon
                                            contentDescription = "Live Conversation",
                                            tint = if (isDarkTheme) Color(0xFFE3E3E3) else Color(0xFF0F172A),
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
