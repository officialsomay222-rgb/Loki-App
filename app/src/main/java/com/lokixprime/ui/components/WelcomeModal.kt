package com.lokixprime.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.theme.Montserrat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeModal(
    isOpen: Boolean,
    onClose: (String) -> Unit,
    isDarkTheme: Boolean = true // True matching the typical Loki dark mode
) {
    val focusRequester = remember { FocusRequester() }
    var tempName by remember { mutableStateOf("") }

    // Focus automatically when modal opens
    LaunchedEffect(isOpen) {
        if (isOpen) {
            tempName = ""
            delay(300) // wait for animation
            focusRequester.requestFocus()
        }
    }

    val handleSubmit = {
        val finalName = tempName.trim().ifEmpty { "Commander" }
        onClose(finalName)
    }

    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f)) // Backdrop blur equivalent (could use custom blur modifier if available)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {} // Intercept clicks to avoid closing when tapping backdrop
                )
                .windowInsetsPadding(WindowInsets.systemBars), // respect safe areas like padding in react
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = isOpen,
                enter = scaleIn(initialScale = 0.9f, animationSpec = spring(dampingRatio = 0.75f, stiffness = 300f)) +
                        fadeIn(animationSpec = tween(200)) +
                        slideInVertically(initialOffsetY = { 50 }, animationSpec = spring(dampingRatio = 0.75f, stiffness = 300f)),
                exit = scaleOut(targetScale = 0.9f) + fadeOut() + slideOutVertically(targetOffsetY = { -50 })
            ) {
                // Main Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp) // clamping horizontal space
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (isDarkTheme) Color(0xFF0A0A0A) else Color.White)
                        .border(
                            1.dp,
                            if (isDarkTheme) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0),
                            RoundedCornerShape(24.dp)
                        )
                        .shadow(if (isDarkTheme) 50.dp else 20.dp, RoundedCornerShape(24.dp), ambientColor = Color.Black, spotColor = Color.Black)
                ) {
                    // Background decorative elements
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Cyan blob
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .offset(x = 100.dp, y = (-50).dp)
                                .align(Alignment.TopEnd)
                                .background(Color(0xFF06B6D4).copy(alpha = 0.2f), CircleShape)
                                .blur(80.dp)
                        )
                        // Violet blob
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .offset(x = (-50).dp, y = 100.dp)
                                .align(Alignment.BottomStart)
                                .background(Color(0xFF8B5CF6).copy(alpha = 0.2f), CircleShape)
                                .blur(80.dp)
                        )
                    }

                    // Content
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Sparkles Icon container
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(Color(0xFF06B6D4), Color(0xFF8B5CF6))
                                    )
                                )
                                .shadow(8.dp, CircleShape, spotColor = Color(0xFF06B6D4).copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = LokiIcons.Sparkles,
                                contentDescription = "Welcome",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Welcome to LOKI X PRIME",
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = if (isDarkTheme) Color.White else Color(0xFF0F172A),
                            textAlign = TextAlign.Center,
                            letterSpacing = (-0.5).sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Before we begin our journey, how should I address you?",
                            fontFamily = Montserrat,
                            fontSize = 14.sp,
                            color = if (isDarkTheme) Color(0xFFA1A1AA) else Color(0xFF64748B),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Input Field Container
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = tempName,
                                onValueChange = { tempName = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(focusRequester),
                                placeholder = {
                                    Text(
                                        text = "Enter your name (e.g., Commander)",
                                        color = if (isDarkTheme) Color(0xFF52525B) else Color(0xFF94A3B8),
                                        fontSize = 14.sp
                                    )
                                },
                                textStyle = LocalTextStyle.current.copy(
                                    color = if (isDarkTheme) Color.White else Color(0xFF0F172A),
                                    fontSize = 14.sp
                                ),
                                shape = RoundedCornerShape(16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = if (isDarkTheme) Color(0xFF1C1C1C) else Color.White,
                                    unfocusedContainerColor = if (isDarkTheme) Color(0xFF161616) else Color(0xFFF8FAFC),
                                    focusedBorderColor = Color(0xFF06B6D4).copy(alpha = 0.5f),
                                    unfocusedBorderColor = if (isDarkTheme) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0),
                                ),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(onDone = { handleSubmit() }),
                                singleLine = true
                            )

                            // Pulsing dot
                            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                            val alpha by infiniteTransition.animateFloat(
                                initialValue = 0.4f,
                                targetValue = 1f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(1000),
                                    repeatMode = RepeatMode.Reverse
                                ),
                                label = "dotPulse"
                            )

                            androidx.compose.animation.AnimatedVisibility(
                                visible = tempName.trim().isNotEmpty(),
                                enter = fadeIn(),
                                exit = fadeOut(),
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(Color(0xFF06B6D4).copy(alpha = alpha), CircleShape)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Submit Button
                        val interactionSource = remember { MutableInteractionSource() }
                        val isPressed by interactionSource.collectIsPressedAsState()
                        val scale by animateFloatAsState(
                            targetValue = if (isPressed) 0.98f else 1f,
                            animationSpec = spring(dampingRatio = 0.75f, stiffness = 400f),
                            label = "buttonScale"
                        )

                        Button(
                            onClick = { handleSubmit() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .scale(scale),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent, // using background instead for gradient
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(0.dp), // let background fill
                            interactionSource = interactionSource
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.horizontalGradient(
                                            colors = listOf(Color(0xFF0891B2), Color(0xFF7C3AED))
                                        )
                                    )
                                    .shadow(8.dp, CircleShape, spotColor = Color(0xFF06B6D4).copy(alpha = 0.25f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Start Experience",
                                        fontFamily = Montserrat,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(
                                        imageVector = LokiIcons.ArrowRight,
                                        contentDescription = null,
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
}
