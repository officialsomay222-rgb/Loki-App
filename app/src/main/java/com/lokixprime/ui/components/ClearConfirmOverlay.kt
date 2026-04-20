package com.lokixprime.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.icons.LokiIcons

@Composable
fun ClearConfirmOverlay(
    visible: Boolean,
    onParentClose: () -> Unit,
    onClose: () -> Unit,
    onClearAllChats: () -> Unit,
    setShowClearConfirm: (Boolean) -> Unit
) {
    val isDark = isSystemInDarkTheme()

    val backdropColor = if (isDark) Color(0xF2000000) else Color(0xE6FFFFFF) // bg-white/90 dark:bg-black/95
    val containerBg = if (isDark) Color(0xFF0A0A0A) else Color.White // bg-white dark:bg-[#0a0a0a]
    val borderColor = if (isDark) Color(0x1AFFFFFF) else Color(0xFFE2E8F0) // border-slate-200 dark:border-white/10
    val titleColor = if (isDark) Color.White else Color(0xFF0F172A) // text-slate-900 dark:text-white
    val descColor = if (isDark) Color(0xFF717171) else Color(0xFF64748B) // text-slate-500 dark:text-[#717171]
    val cancelBg = if (isDark) Color(0x0DFFFFFF) else Color(0xFFF1F5F9) // bg-slate-100 dark:bg-white/5
    val cancelText = if (isDark) Color.White else Color(0xFF0F172A) // text-slate-900 dark:text-white

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(200)),
        exit = fadeOut(tween(200)),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backdropColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {} // block clicks from falling through
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = scaleIn(initialScale = 0.9f, animationSpec = tween(200)) + fadeIn(tween(200)),
                exit = scaleOut(targetScale = 0.9f, animationSpec = tween(200)) + fadeOut(tween(200))
            ) {
                Column(
                    modifier = Modifier
                        .widthIn(max = 384.dp) // max-w-sm
                        .fillMaxWidth()
                        .shadow(
                            elevation = if (isDark) 50.dp else 20.dp,
                            shape = RoundedCornerShape(32.dp),
                            spotColor = if (isDark) Color.Black else Color.Black.copy(alpha = 0.25f),
                            ambientColor = if (isDark) Color.Black else Color.Black.copy(alpha = 0.25f)
                        )
                        .background(containerBg, RoundedCornerShape(32.dp))
                        .border(1.dp, borderColor, RoundedCornerShape(32.dp))
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(Color(0x1AEF4444), CircleShape), // bg-red-500/10
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = LokiIcons.Trash2,
                            contentDescription = "Trash",
                            tint = Color(0xFFEF4444), // text-red-500
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Clear History?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = titleColor,
                        textAlign = TextAlign.Center,
                        letterSpacing = (-0.5).sp // tracking-tight
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "This will permanently delete all your chat sessions. This action cannot be undone.",
                        fontSize = 14.sp,
                        color = descColor,
                        textAlign = TextAlign.Center,
                        lineHeight = 22.sp // leading-relaxed
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        val clearInteractionSource = remember { MutableInteractionSource() }
                        val isClearPressed by clearInteractionSource.collectIsPressedAsState()

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .scale(if (isClearPressed) 0.98f else 1f)
                                .shadow(
                                    elevation = 20.dp,
                                    shape = CircleShape,
                                    spotColor = Color(0x33DC2626), // shadow-red-600/20
                                    ambientColor = Color(0x33DC2626)
                                )
                                .background(Color(0xFFDC2626), CircleShape) // bg-red-600
                                .clip(CircleShape)
                                .clickable(
                                    interactionSource = clearInteractionSource,
                                    indication = null,
                                    onClick = {
                                        onClearAllChats()
                                        onParentClose()
                                        setShowClearConfirm(false)
                                    }
                                )
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Clear Everything",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }

                        val cancelInteractionSource = remember { MutableInteractionSource() }
                        val isCancelPressed by cancelInteractionSource.collectIsPressedAsState()

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .scale(if (isCancelPressed) 0.98f else 1f)
                                .background(cancelBg, CircleShape)
                                .clip(CircleShape)
                                .clickable(
                                    interactionSource = cancelInteractionSource,
                                    indication = null,
                                    onClick = { setShowClearConfirm(false) }
                                )
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Cancel",
                                color = cancelText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
