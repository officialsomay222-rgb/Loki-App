package com.lokixprime.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.theme.Inter
import com.lokixprime.ui.theme.LokiCyan
import com.lokixprime.ui.theme.Montserrat

@Composable
fun AppsModal(
    isOpen: Boolean,
    onClose: () -> Unit,
    commanderName: String
) {
    val uriHandler = LocalUriHandler.current

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
                    onClick = {} // block clicks
                )
                .padding(top = 48.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
                // In React: clamp(24px, safe-area-top, 48px), etc.
                // A statusBarsPadding + navigationBarsPadding setup is ideal but Box padding handles general case well
                .windowInsetsPadding(WindowInsets.safeDrawing),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = isOpen,
                enter = scaleIn(initialScale = 0.9f, animationSpec = tween(300)) + fadeIn(animationSpec = tween(300)),
                exit = scaleOut(targetScale = 0.9f, animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
            ) {
                Box(
                    modifier = Modifier
                        .widthIn(max = 896.dp) // max-w-4xl is 56rem (896px)
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f) // max-h-[85vh] roughly 85% of screen height
                        .background(Color(0xFF0A0A0C), RoundedCornerShape(32.dp)) // bg-[#0a0a0c] rounded-[2rem]
                        .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
                        .shadow(
                            elevation = 50.dp,
                            shape = RoundedCornerShape(32.dp),
                            spotColor = Color.Black.copy(alpha = 0.5f)
                        )
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Scrollable content
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                                .padding(32.dp), // p-8 sm:p-12
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            // Krishna Avtaar Image
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                // Pulse effect blur
                                val infiniteTransition = rememberInfiniteTransition(label = "pulse_blur")
                                val pulseAlpha by infiniteTransition.animateFloat(
                                    initialValue = 0.4f,
                                    targetValue = 0.8f,
                                    animationSpec = infiniteRepeatable(
                                        animation = tween(2000, easing = LinearEasing),
                                        repeatMode = RepeatMode.Reverse
                                    ),
                                    label = "pulse_alpha"
                                )
                                Box(
                                    modifier = Modifier
                                        .size(176.dp) // larger than image to mimic -inset-4
                                        .background(Color(0xFF06B6D4).copy(alpha = pulseAlpha * 0.2f), CircleShape) // cyan-500/20
                                        // A blur modifier could go here in compose 1.1+, but skipping for general compatibility.
                                        // The background itself serves as a soft glow.
                                )

                                AsyncImage(
                                    model = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
                                    contentDescription = "Krishna Avtaar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(160.dp) // w-32 h-32 sm:w-40 sm:h-40 (40rem = 160px)
                                        .clip(CircleShape)
                                        .border(4.dp, Color(0xFF06B6D4).copy(alpha = 0.5f), CircleShape) // border-cyan-500/50
                                        .shadow(
                                            elevation = 30.dp,
                                            shape = CircleShape,
                                            spotColor = Color(0xFF00F2FF).copy(alpha = 0.3f)
                                        )
                                )
                            }

                            // Stylish Animated Name
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(bottom = 32.dp)
                            ) {
                                // Gradient text logic
                                val textGradient = Brush.linearGradient(
                                    colors = listOf(Color.White, Color(0xFF22D3EE), Color(0xFF3B82F6)) // from-white via-cyan-400 to-blue-500
                                )
                                Text(
                                    text = "Somay a.k.a. Owner",
                                    fontSize = 48.sp, // sm:text-5xl
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = (-1.5).sp, // tracking-tighter
                                    modifier = Modifier
                                        .graphicsLayer(alpha = 0.99f)
                                        .drawWithCache {
                                            onDrawWithContent {
                                                drawContent()
                                                drawRect(
                                                    brush = textGradient,
                                                    blendMode = BlendMode.SrcAtop
                                                )
                                            }
                                        }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Digital Architect & Visionary".uppercase(),
                                    color = Color(0xFF06B6D4).copy(alpha = 0.8f), // text-cyan-500/80
                                    fontFamily = Inter, // using Inter for mono-like clean text
                                    fontSize = 14.sp,
                                    letterSpacing = 4.8.sp, // tracking-[0.3em]
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            // Greeting Message
                            Text(
                                text = "Namaste! Welcome to my digital ecosystem. I'm passionate about building cutting-edge experiences that push the boundaries of what's possible. Explore our latest creations below.",
                                color = Color(0xFF94A3B8), // text-slate-400
                                fontSize = 18.sp,
                                lineHeight = 28.sp, // leading-relaxed
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(bottom = 32.dp)
                                    .widthIn(max = 672.dp) // max-w-2xl
                            )

                            // Apps Section
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.padding(bottom = 24.dp)
                                ) {
                                    Icon(
                                        imageVector = LokiIcons.Sparkles,
                                        contentDescription = null,
                                        tint = Color(0xFFEAB308), // text-yellow-500
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = "Try Our Another Apps",
                                        color = Color.White,
                                        fontSize = 20.sp, // text-xl
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                // Apps Grid
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(24.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    // App Card: Commerce Prime
                                    val commerceInteractionSource = remember { MutableInteractionSource() }
                                    val isCommerceHovered by commerceInteractionSource.collectIsPressedAsState()
                                    val commerceScale by animateFloatAsState(targetValue = if (isCommerceHovered) 1.02f else 1f)

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .scale(commerceScale)
                                            .background(Color.White.copy(alpha = if(isCommerceHovered) 0.1f else 0.05f), RoundedCornerShape(16.dp))
                                            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                            .clip(RoundedCornerShape(16.dp))
                                            .clickable(interactionSource = commerceInteractionSource, indication = null) {
                                                uriHandler.openUri("https://commerce-prime.vercel.app/")
                                            }
                                            .padding(24.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    Brush.linearGradient(listOf(Color(0xFF06B6D4).copy(alpha=0.2f), Color(0xFF2563EB).copy(alpha=0.2f))),
                                                    RoundedCornerShape(12.dp)
                                                )
                                                .padding(12.dp)
                                        ) {
                                            Icon(
                                                imageVector = LokiIcons.Rocket,
                                                contentDescription = null,
                                                tint = Color(0xFF06B6D4), // text-cyan-500
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = "Commerce Prime",
                                                color = if (isCommerceHovered) Color(0xFF22D3EE) else Color.White, // hover:text-cyan-400
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "A premium e-commerce experience with advanced features.",
                                                color = Color(0xFF64748B), // text-slate-500
                                                fontSize = 14.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Icon(
                                            imageVector = LokiIcons.ExternalLink,
                                            contentDescription = null,
                                            tint = if (isCommerceHovered) Color.White else Color(0xFF475569), // text-slate-600 group-hover:text-white
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }

                                    // Coming Soon Card
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .graphicsLayer(alpha = 0.6f) // opacity-60
                                            .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                                            // Technically dashed border in CSS, using solid here for simplicity in standard Compose
                                            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                                            .padding(24.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(48.dp)
                                                .background(Color.White.copy(alpha = 0.05f), CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = LokiIcons.AppWindow,
                                                contentDescription = null,
                                                tint = Color(0xFF64748B), // text-slate-500
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "More Apps Coming Soon",
                                            color = Color(0xFF94A3B8), // text-slate-400
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "UNDER DEVELOPMENT",
                                            color = Color(0xFF475569), // text-slate-600
                                            fontSize = 12.sp,
                                            letterSpacing = 1.2.sp, // tracking-widest
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }

                        // Footer
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF0A0A0C))
                                .border(1.dp, Color.White.copy(alpha = 0.05f))
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "BUILT WITH PASSION BY SOMAY • 2026",
                                color = Color(0xFF475569), // text-slate-600
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 1.2.sp // tracking-widest
                            )
                        }
                    }

                    // Close Button
                    val closeInteractionSource = remember { MutableInteractionSource() }
                    val isCloseHovered by closeInteractionSource.collectIsPressedAsState()
                    val closeRotation by animateFloatAsState(targetValue = if (isCloseHovered) 90f else 0f)

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(24.dp)
                            .background(if (isCloseHovered) Color.White.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.05f), CircleShape)
                            .clip(CircleShape)
                            .clickable(interactionSource = closeInteractionSource, indication = null, onClick = onClose)
                            .padding(12.dp)
                    ) {
                        Icon(
                            imageVector = LokiIcons.X,
                            contentDescription = "Close Apps Menu",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .graphicsLayer(rotationZ = closeRotation)
                        )
                    }
                }
            }
        }
    }
}
