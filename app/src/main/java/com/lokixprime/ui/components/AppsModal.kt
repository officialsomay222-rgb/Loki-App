package com.lokixprime.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.modifiers.glassmorphism
import com.lokixprime.ui.theme.Inter
import com.lokixprime.ui.theme.JetBrainsMono
import com.lokixprime.ui.theme.Montserrat
import kotlinx.coroutines.delay

data class AppItem(
    val name: String,
    val description: String,
    val link: String,
    val icon: @Composable () -> Unit,
    val colorStart: Color,
    val colorEnd: Color
)

@Composable
fun AppsModal(
    isOpen: Boolean,
    onClose: () -> Unit,
    commanderName: String
) {
    val context = LocalContext.current

    val apps = listOf(
        AppItem(
            name = "Commerce Prime",
            description = "A premium e-commerce experience with advanced features.",
            link = "https://commerce-prime.vercel.app/",
            icon = { Icon(imageVector = LokiIcons.Rocket, contentDescription = null, tint = Color(0xFF06b6d4), modifier = Modifier.size(24.dp)) },
            colorStart = Color(0xFF06b6d4),
            colorEnd = Color(0xFF2563eb)
        )
    )

    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { /* Intercept clicks behind modal */ }
                )
                .padding(horizontal = 16.dp, vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = isOpen,
                enter = scaleIn(initialScale = 0.9f, animationSpec = tween(300, easing = FastOutSlowInEasing)) + slideInVertically(initialOffsetY = { 50 }, animationSpec = tween(300, easing = FastOutSlowInEasing)) + fadeIn(animationSpec = tween(300)),
                exit = scaleOut(targetScale = 0.9f, animationSpec = tween(300, easing = FastOutSlowInEasing)) + slideOutVertically(targetOffsetY = { 50 }, animationSpec = tween(300, easing = FastOutSlowInEasing)) + fadeOut(animationSpec = tween(300))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 600.dp) // approx max-[85vh] on many devices
                        .glassmorphism(
                            shape = RoundedCornerShape(32.dp),
                            backgroundColor = Color(0xFF0a0a0c),
                            borderColor = Color.White.copy(alpha = 0.1f)
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Close Button
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            val interactionSource = remember { MutableInteractionSource() }
                            val isHovered by interactionSource.collectIsHoveredAsState()
                            val rotation by animateFloatAsState(
                                targetValue = if (isHovered) 90f else 0f,
                                animationSpec = tween(300),
                                label = "rotation"
                            )
                            IconButton(
                                onClick = onClose,
                                interactionSource = interactionSource,
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.05f), CircleShape)
                                    .graphicsLayer { rotationZ = rotation }
                            ) {
                                Icon(
                                    imageVector = LokiIcons.X,
                                    contentDescription = "Close Apps Menu",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        // Content Container
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 32.dp, vertical = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Krishna Avtaar Image
                            var imageVisible by remember { mutableStateOf(false) }
                            LaunchedEffect(isOpen) {
                                if (isOpen) {
                                    delay(200)
                                    imageVisible = true
                                }
                            }

                            AnimatedVisibility(
                                visible = imageVisible,
                                enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { -50 }, animationSpec = tween(500))
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                                    val pulseScale by infiniteTransition.animateFloat(
                                        initialValue = 1f,
                                        targetValue = 1.2f,
                                        animationSpec = infiniteRepeatable(
                                            animation = tween(2000),
                                            repeatMode = RepeatMode.Reverse
                                        ),
                                        label = "pulseScale"
                                    )

                                    Box(
                                        modifier = Modifier
                                            .size(160.dp)
                                            .scale(pulseScale)
                                            .blur(24.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                                            .background(Color(0xFF06b6d4).copy(alpha = 0.2f), CircleShape)
                                    )

                                    AsyncImage(
                                        model = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
                                        contentDescription = "Krishna Avtaar",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(140.dp)
                                            .clip(CircleShape)
                                            .border(4.dp, Color(0xFF06b6d4).copy(alpha = 0.5f), CircleShape)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Stylish Animated Name
                            var textVisible by remember { mutableStateOf(false) }
                            LaunchedEffect(isOpen) {
                                if (isOpen) {
                                    delay(400)
                                    textVisible = true
                                }
                            }

                            AnimatedVisibility(
                                visible = textVisible,
                                enter = fadeIn(animationSpec = tween(500))
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    val infiniteTransition = rememberInfiniteTransition(label = "gradient")
                                    val gradientOffset by infiniteTransition.animateFloat(
                                        initialValue = 0f,
                                        targetValue = 1000f,
                                        animationSpec = infiniteRepeatable(
                                            animation = tween(3000, easing = LinearEasing),
                                            repeatMode = RepeatMode.Reverse
                                        ),
                                        label = "gradientOffset"
                                    )

                                    val gradientBrush = Brush.linearGradient(
                                        colors = listOf(Color.White, Color(0xFF22d3ee), Color(0xFF3b82f6)),
                                        start = Offset(gradientOffset, 0f),
                                        end = Offset(gradientOffset + 500f, 0f)
                                    )

                                    Text(
                                        text = "Somay a.k.a. Owner",
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Black,
                                        fontSize = 32.sp,
                                        style = TextStyle(brush = gradientBrush),
                                        letterSpacing = (-1).sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    var subtitleVisible by remember { mutableStateOf(false) }
                                    LaunchedEffect(isOpen) {
                                        if(isOpen){
                                            delay(500)
                                            subtitleVisible = true
                                        }
                                    }
                                    AnimatedVisibility(visible = subtitleVisible, enter = fadeIn(tween(500))) {
                                        Text(
                                            text = "DIGITAL ARCHITECT & VISIONARY",
                                            fontFamily = JetBrainsMono,
                                            color = Color(0xFF06b6d4).copy(alpha = 0.8f),
                                            fontSize = 12.sp,
                                            letterSpacing = 4.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Greeting Message
                            var greetingVisible by remember { mutableStateOf(false) }
                            LaunchedEffect(isOpen) {
                                if (isOpen) {
                                    delay(600)
                                    greetingVisible = true
                                }
                            }

                            AnimatedVisibility(
                                visible = greetingVisible,
                                enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { 20 }, animationSpec = tween(500))
                            ) {
                                Text(
                                    text = "Namaste! Welcome to my digital ecosystem. I'm passionate about building cutting-edge experiences that push the boundaries of what's possible. Explore our latest creations below.",
                                    fontFamily = Montserrat,
                                    color = Color(0xFF94a3b8),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 24.sp,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Apps Section
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                                Icon(imageVector = LokiIcons.Sparkles, contentDescription = null, tint = Color(0xFFeab308), modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Try Our Another Apps",
                                    fontFamily = Inter,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(24.dp)) {
                                apps.forEachIndexed { index, app ->
                                    var appVisible by remember { mutableStateOf(false) }
                                    LaunchedEffect(isOpen) {
                                        if (isOpen) {
                                            delay(700L + (index * 100))
                                            appVisible = true
                                        }
                                    }

                                    AnimatedVisibility(
                                        visible = appVisible,
                                        enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { 20 }, animationSpec = tween(500))
                                    ) {
                                        val interactionSource = remember { MutableInteractionSource() }
                                        val isHovered by interactionSource.collectIsHoveredAsState()

                                        val scale by animateFloatAsState(
                                            targetValue = if (isHovered) 1.02f else 1f,
                                            label = "scale"
                                        )

                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .scale(scale)
                                                .background(Color.White.copy(alpha = if(isHovered) 0.1f else 0.05f), RoundedCornerShape(16.dp))
                                                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                                .clickable(interactionSource = interactionSource, indication = null) {
                                                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(app.link)))
                                                }
                                                .padding(24.dp)
                                        ) {
                                            Row(verticalAlignment = Alignment.Top) {
                                                Box(
                                                    modifier = Modifier
                                                        .background(
                                                            Brush.linearGradient(colors = listOf(app.colorStart, app.colorEnd)),
                                                            RoundedCornerShape(12.dp),
                                                            alpha = 0.2f
                                                        )
                                                        .padding(12.dp)
                                                ) {
                                                    app.icon()
                                                }
                                                Spacer(modifier = Modifier.width(16.dp))
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(
                                                        text = app.name,
                                                        fontFamily = Inter,
                                                        fontWeight = FontWeight.Bold,
                                                        color = if(isHovered) Color(0xFF22d3ee) else Color.White,
                                                        fontSize = 16.sp
                                                    )
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    Text(
                                                        text = app.description,
                                                        fontFamily = Montserrat,
                                                        color = Color(0xFF64748b),
                                                        fontSize = 14.sp
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(16.dp))
                                                Icon(
                                                    imageVector = LokiIcons.ExternalLink,
                                                    contentDescription = null,
                                                    tint = if(isHovered) Color.White else Color(0xFF475569),
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                                // Coming Soon Card
                                var comingSoonVisible by remember { mutableStateOf(false) }
                                LaunchedEffect(isOpen) {
                                    if (isOpen) {
                                        delay(800)
                                        comingSoonVisible = true
                                    }
                                }
                                AnimatedVisibility(
                                    visible = comingSoonVisible,
                                    enter = fadeIn(animationSpec = tween(500)) + slideInVertically(initialOffsetY = { 20 }, animationSpec = tween(500))
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                                            .drawBehind {
                                                val stroke = Stroke(width = 2.dp.toPx(), pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
                                                drawRoundRect(color = Color.White.copy(alpha = 0.2f), style = stroke, cornerRadius = CornerRadius(16.dp.toPx()))
                                            }
                                            .padding(24.dp)
                                            .alpha(0.6f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Box(
                                                modifier = Modifier
                                                    .size(48.dp)
                                                    .background(Color.White.copy(alpha = 0.05f), CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(imageVector = LokiIcons.AppWindow, contentDescription = null, tint = Color(0xFF64748b), modifier = Modifier.size(24.dp))
                                            }
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "More Apps Coming Soon",
                                                fontFamily = Inter,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF94a3b8),
                                                fontSize = 16.sp
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = "UNDER DEVELOPMENT",
                                                fontFamily = JetBrainsMono,
                                                color = Color(0xFF475569),
                                                fontSize = 10.sp,
                                                letterSpacing = 2.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Footer
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .drawBehind {
                                    drawLine(
                                        color = Color.White.copy(alpha = 0.05f),
                                        start = Offset(0f, 0f),
                                        end = Offset(size.width, 0f),
                                        strokeWidth = 1.dp.toPx()
                                    )
                                }
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "BUILT WITH PASSION BY SOMAY • 2026",
                                fontFamily = JetBrainsMono,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF475569),
                                fontSize = 10.sp,
                                letterSpacing = 2.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
