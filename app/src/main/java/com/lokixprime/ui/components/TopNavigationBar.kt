package com.lokixprime.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.modifiers.rgbAura
import com.lokixprime.ui.theme.BackgroundDark
import com.lokixprime.ui.theme.LokiCyan
import com.lokixprime.ui.theme.Montserrat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopNavigationBar(
    onSettingsClick: () -> Unit,
    onMenuClick: () -> Unit,
    isAwakenedMode: Boolean,
    onAvatarClick: () -> Unit,
    onTitleLongClick: () -> Unit = {},
    isLoading: Boolean = false,
    isGeneratingImage: Boolean = false // Matches web condition: isLoading && lastMessage.role == model && secondToLastMessage.isImage
) {
    // In React: isDark = resolvedTheme === "dark"
    val isDark = isSystemInDarkTheme() || isAwakenedMode
    val textColor = if (isDark) Color(0xFFE0E0E0) else Color(0xFF0F172A) // slate-900
    val iconColor = if (isDark) Color.White else Color(0xFF475569) // slate-600

    val bgColor = if (isDark) Color(0xFF08080C).copy(alpha = 0.8f) else Color.White.copy(alpha = if (isAwakenedMode) 0.9f else 0.8f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(64.dp)
            .background(bgColor)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left side: Menu trigger (flex-1 in web)
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            IconButton(
                onClick = onMenuClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = LokiIcons.PanelLeftOpen, // Updated to match PanelLeftOpen
                    contentDescription = "Menu",
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Center: Title (shrink-0 in web)
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .combinedClickable(
                    onLongClick = onTitleLongClick,
                    onClick = {}
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading && isGeneratingImage) {
                // GENERATING state
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    HeaderInfinityLogo(modifier = Modifier.size(width = 40.dp, height = 20.dp).padding(end = 12.dp))

                    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                    val pulseAlpha by infiniteTransition.animateFloat(
                        initialValue = 0.5f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = LinearEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "pulseText"
                    )

                    Text(
                        text = "GENERATING",
                        color = LokiCyan,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Montserrat,
                        fontSize = 16.sp, // sm:text-xl
                        letterSpacing = 4.sp,
                        modifier = Modifier.alpha(pulseAlpha)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    // Bouncing dots
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        for (i in 0..2) {
                            val dotTransition = rememberInfiniteTransition(label = "dot$i")
                            val offset by dotTransition.animateFloat(
                                initialValue = 0f,
                                targetValue = -6f,
                                animationSpec = infiniteRepeatable(
                                    animation = keyframes {
                                        durationMillis = 600
                                        0f at 0
                                        -6f at 300 with FastOutSlowInEasing
                                        0f at 600
                                    },
                                    initialStartOffset = StartOffset(i * 150)
                                ),
                                label = "dotOffset"
                            )
                            Box(
                                modifier = Modifier
                                    .size(6.dp) // sm:w-1.5 sm:h-1.5
                                    .offset(y = offset.dp)
                                    .background(LokiCyan, CircleShape)
                            )
                        }
                    }
                }
            } else {
                // Normal title state
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "LOKI",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Montserrat,
                        fontSize = 18.sp, // sm:text-2xl is 24sp, matching roughly 18sp mobile
                        letterSpacing = 4.sp
                    )

                    HeaderInfinityLogo(modifier = Modifier.size(width = 56.dp, height = 32.dp).padding(horizontal = 16.dp)) // w-14 h-8

                    Surface(
                        color = Color(0xFF06B6D4).copy(alpha = 0.1f), // cyan-500/10
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, if (isDark) LokiCyan.copy(alpha = 0.5f) else Color(0xFF06B6D4).copy(alpha = 0.5f)),
                        modifier = Modifier.shadow(
                            elevation = 12.dp,
                            shape = RoundedCornerShape(8.dp),
                            spotColor = if (isDark) LokiCyan.copy(alpha = 0.4f) else LokiCyan.copy(alpha = 0.3f)
                        )
                    ) {
                        Text(
                            text = "PRIME",
                            color = if (isDark) LokiCyan else Color(0xFF0891B2), // cyan-600 in light, #00f2ff in dark
                            fontWeight = FontWeight.Black,
                            fontFamily = Montserrat,
                            fontSize = 12.sp, // sm:text-[0.75rem]
                            letterSpacing = 3.sp,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }

        // Right side: Avatar (flex-1 justify-end)
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            val scale by animateFloatAsState(
                targetValue = if (isPressed) 1.1f else 1f, // hover:scale-110 equivalent
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "avatarScale"
            )
            val avatarAlpha by animateFloatAsState(
                targetValue = if (isAwakenedMode) 0f else 1f,
                animationSpec = tween(300),
                label = "avatarAlpha"
            )

            Box(
                modifier = Modifier
                    .size(48.dp) // sm:w-12 sm:h-12
                    .scale(scale)
                    .alpha(avatarAlpha)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onAvatarClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = BackgroundDark,
                            shape = CircleShape
                        )
                ) {
                    AsyncImage(
                        model = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
                        contentDescription = "Commander Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
