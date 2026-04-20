package com.lokixprime.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.icons.LokiIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeModal(
    isOpen: Boolean,
    onClose: (String) -> Unit
) {
    var tempName by remember { mutableStateOf("") }

    LaunchedEffect(isOpen) {
        if (isOpen) {
            tempName = ""
        }
    }

    val isDark = isSystemInDarkTheme()

    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300)),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {}
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = isOpen,
                enter = scaleIn(initialScale = 0.9f, animationSpec = spring(dampingRatio = 0.8f, stiffness = 300f)) + fadeIn(),
                exit = scaleOut(targetScale = 0.9f, animationSpec = tween(200)) + fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 448.dp) // max-w-md
                        .shadow(
                            elevation = if (isDark) 50.dp else 24.dp,
                            shape = RoundedCornerShape(24.dp),
                            spotColor = if (isDark) Color.Black else Color.Black.copy(alpha = 0.25f),
                            ambientColor = if (isDark) Color.Black else Color.Black.copy(alpha = 0.25f)
                        )
                        .background(
                            if (isDark) Color(0xFF0A0A0A) else Color.White,
                            RoundedCornerShape(24.dp)
                        )
                        .border(
                            1.dp,
                            if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0),
                            RoundedCornerShape(24.dp)
                        )
                ) {
                    // Decorative blurry blobs
                    Box(
                        modifier = Modifier
                            .offset(x = 120.dp, y = (-80).dp)
                            .size(256.dp) // w-64 h-64
                            .background(Color(0xFF06B6D4).copy(alpha = 0.2f), CircleShape) // cyan-500/20
                            .blur(80.dp)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset(x = (-80).dp, y = 80.dp)
                            .size(256.dp) // w-64 h-64
                            .background(Color(0xFF8B5CF6).copy(alpha = 0.2f), CircleShape) // violet-500/20
                            .blur(80.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Sparkles Icon Box
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .shadow(
                                    elevation = 16.dp,
                                    shape = CircleShape,
                                    spotColor = Color(0xFF06B6D4).copy(alpha = 0.3f)
                                )
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xFF06B6D4), Color(0xFF8B5CF6)) // cyan-500 to violet-500
                                    ),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = LokiIcons.Sparkles,
                                contentDescription = "Sparkles",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Welcome to LOKI X PRIME",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.5).sp,
                            color = if (isDark) Color.White else Color(0xFF0F172A),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Before we begin our journey, how should I address you?",
                            fontSize = 14.sp,
                            color = if (isDark) Color(0xFFA1A1AA) else Color(0xFF64748B),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )

                        // Input field
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                            TextField(
                                value = tempName,
                                onValueChange = { tempName = it },
                                placeholder = {
                                    Text(
                                        "Enter your name (e.g., Commander)",
                                        color = if (isDark) Color(0xFF52525B) else Color(0xFF94A3B8), // placeholder-zinc-600 / placeholder-slate-400
                                        fontSize = 14.sp
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp), // rounded-2xl
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = if (isDark) Color(0xFF1C1C1C) else Color.White,
                                    unfocusedContainerColor = if (isDark) Color(0xFF161616) else Color(0xFFF8FAFC), // slate-50
                                    focusedTextColor = if (isDark) Color.White else Color(0xFF0F172A),
                                    unfocusedTextColor = if (isDark) Color.White else Color(0xFF0F172A),
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        1.dp,
                                        if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0),
                                        RoundedCornerShape(16.dp)
                                    ),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        val finalName = if (tempName.trim().isEmpty()) "Commander" else tempName.trim()
                                        onClose(finalName)
                                    }
                                )
                            )

                            // Pulsing dot inside input
                            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                            val pulseAlpha by infiniteTransition.animateFloat(
                                initialValue = 0.4f,
                                targetValue = 1f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(1000, easing = LinearEasing),
                                    repeatMode = RepeatMode.Reverse
                                ),
                                label = "dotPulse"
                            )

                            androidx.compose.animation.AnimatedVisibility(
                                visible = tempName.trim().isNotEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier.padding(end = 16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(Color(0xFF06B6D4).copy(alpha = pulseAlpha), CircleShape)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Submit Button
                        val submitInteractionSource = remember { MutableInteractionSource() }
                        val isSubmitPressed by submitInteractionSource.collectIsPressedAsState()
                        val submitScale by animateFloatAsState(
                            targetValue = if (isSubmitPressed) 0.98f else 1f,
                            label = "submitScale"
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .scale(submitScale)
                                .shadow(
                                    elevation = 16.dp,
                                    shape = CircleShape,
                                    spotColor = Color(0xFF06B6D4).copy(alpha = 0.25f)
                                )
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xFF0891B2), Color(0xFF7C3AED)) // cyan-600 to violet-600
                                    ),
                                    CircleShape
                                )
                                .clip(CircleShape)
                                .clickable(
                                    interactionSource = submitInteractionSource,
                                    indication = null,
                                    onClick = {
                                        val finalName = if (tempName.trim().isEmpty()) "Commander" else tempName.trim()
                                        onClose(finalName)
                                    }
                                )
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Start Experience",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                // Animate icon translation on hover (simulating group-hover:translate-x-1)
                                // In mobile, maybe just slightly animated always or when pressed. Let's just put it here
                                Icon(
                                    imageVector = LokiIcons.ArrowRight,
                                    contentDescription = "Start",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
