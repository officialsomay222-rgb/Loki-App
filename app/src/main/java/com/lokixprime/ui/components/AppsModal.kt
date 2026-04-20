package com.lokixprime.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalUriHandler

private val RocketIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Rocket",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(12f, 15f)
            lineTo(12f, 20f)
            curveToRelative(0f, 0f, 3.03f, -0.55f, 4f, -2f)
            curveToRelative(1.08f, -1.62f, 0f, -5f, 0f, -5f)
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(4.5f, 16.5f)
            curveToRelative(-1.5f, 1.26f, -2f, 5f, -2f, 5f)
            reflectiveCurveToRelative(3.74f, -0.5f, 5f, -2f)
            curveToRelative(0.71f, -0.84f, 0.7f, -2.13f, -0.09f, -2.91f)
            arcToRelative(2.18f, 2.18f, 0f, false, false, -2.91f, -0.09f)
            close()
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(9f, 12f)
            arcToRelative(22f, 22f, 0f, false, true, 2f, -3.95f)
            arcTo(12.88f, 12.88f, 0f, false, true, 22f, 2f)
            curveToRelative(0f, 2.72f, -0.78f, 7.5f, -6f, 11f)
            arcToRelative(22.4f, 22.4f, 0f, false, true, -4f, 2f)
            close()
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(9f, 12f)
            lineTo(4f, 12f)
            curveToRelative(0f, 0f, 0.55f, -3.03f, 2f, -4f)
            curveToRelative(1.62f, -1.08f, 5f, 0.05f, 5f, 0.05f)
        }
    }.build()

private val ExternalLinkIcon: ImageVector
    get() = ImageVector.Builder(
        name = "ExternalLink",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(15f, 3f)
            lineTo(21f, 3f)
            lineTo(21f, 9f)
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(10f, 14f)
            lineTo(21f, 3f)
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(18f, 13f)
            lineTo(18f, 19f)
            arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
            lineTo(5f, 21f)
            arcToRelative(2f, 2f, 0f, false, true, -2f, -2f)
            lineTo(3f, 8f)
            arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
            lineTo(11f, 6f)
        }
    }.build()

private val SparklesIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Sparkles",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(11.017f, 2.814f)
            arcToRelative(1f, 1f, 0f, false, true, 1.966f, 0f)
            lineToRelative(1.051f, 5.558f)
            arcToRelative(2f, 2f, 0f, false, false, 1.594f, 1.594f)
            lineToRelative(5.558f, 1.051f)
            arcToRelative(1f, 1f, 0f, false, true, 0f, 1.966f)
            lineToRelative(-5.558f, 1.051f)
            arcToRelative(2f, 2f, 0f, false, false, -1.594f, 1.594f)
            lineToRelative(-1.051f, 5.558f)
            arcToRelative(1f, 1f, 0f, false, true, -1.966f, 0f)
            lineToRelative(-1.051f, -5.558f)
            arcToRelative(2f, 2f, 0f, false, false, -1.594f, -1.594f)
            lineToRelative(-5.558f, -1.051f)
            arcToRelative(1f, 1f, 0f, false, true, 0f, -1.966f)
            lineToRelative(5.558f, -1.051f)
            arcToRelative(2f, 2f, 0f, false, false, 1.594f, -1.594f)
            close()
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(20f, 2f)
            lineTo(20f, 6f)
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(22f, 4f)
            lineTo(18f, 4f)
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(6f, 20f)
            arcToRelative(2f, 2f, 0f, true, false, -4f, 0f)
            arcToRelative(2f, 2f, 0f, true, false, 4f, 0f)
            close()
        }
    }.build()

private val AppWindowIcon: ImageVector
    get() = ImageVector.Builder(
        name = "AppWindow",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(4f, 4f)
            lineTo(20f, 4f)
            arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
            lineTo(22f, 18f)
            arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
            lineTo(4f, 20f)
            arcToRelative(2f, 2f, 0f, false, true, -2f, -2f)
            lineTo(2f, 6f)
            arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
            close()
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(10f, 4f)
            lineTo(10f, 8f)
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(2f, 8f)
            lineTo(22f, 8f)
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(6f, 4f)
            lineTo(6f, 8f)
        }
    }.build()

private val XIcon: ImageVector
    get() = ImageVector.Builder(
        name = "X",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(18f, 6f)
            lineTo(6f, 18f)
        }
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(6f, 6f)
            lineTo(18f, 18f)
        }
    }.build()

@Composable
fun AppsModal(
    isOpen: Boolean,
    onClose: () -> Unit,
    commanderName: String
) {
    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
                .clickable(
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                    indication = null,
                    onClick = onClose
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 48.dp, bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                AppsModalContent(
                    onClose = onClose,
                    commanderName = commanderName
                )
            }
        }
    }
}

@Composable
private fun AppsModalContent(
    onClose: () -> Unit,
    commanderName: String
) {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current

    // Cyan colors
    val cyan400 = Color(0xFF22d3ee)
    val cyan500 = Color(0xFF06b6d4)
    val blue500 = Color(0xFF3b82f6)
    val blue600 = Color(0xFF2563eb)

    // Animate the background blur/pulse
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    // Gradient animation for name
    val gradientX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "gradientX"
    )

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.White, cyan400, blue500),
        start = Offset(gradientX - 1000f, 0f),
        end = Offset(gradientX, 0f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .background(Color(0xFF0a0a0c), RoundedCornerShape(32.dp))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
            .clickable(
                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                indication = null,
                onClick = {} // Consume clicks so they don't propagate to the backdrop
            )
    ) {
        // Content Container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Main content padding
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Krishna Avtaar Image with pulse background
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    // Pulsing backdrop
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .blur(24.dp)
                            .background(cyan500.copy(alpha = pulseAlpha), CircleShape)
                    )

                    AsyncImage(
                        model = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
                        contentDescription = "Krishna Avtaar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(140.dp)
                            .border(4.dp, cyan500.copy(alpha = 0.5f), CircleShape)
                            .clip(CircleShape)
                    )
                }

                // Stylish Animated Name
                Text(
                    text = "Somay a.k.a. Owner",
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = (-1).sp,
                        brush = gradientBrush
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "DIGITAL ARCHITECT & VISIONARY",
                    color = cyan500.copy(alpha = 0.8f),
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    fontSize = 14.sp,
                    letterSpacing = 4.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Greeting Message
                Text(
                    text = "Namaste! Welcome to my digital ecosystem. I'm passionate about building cutting-edge experiences that push the boundaries of what's possible. Explore our latest creations below.",
                    color = Color(0xFF94a3b8), // slate-400
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Apps Section Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = SparklesIcon,
                        contentDescription = null,
                        tint = Color(0xFFeab308), // yellow-500
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Try Our Another Apps",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Commerce Prime Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                        .clickable { uriHandler.openUri("https://commerce-prime.vercel.app/") }
                        .padding(24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        // Icon Container
                        Box(
                            modifier = Modifier
                                .background(
                                    Brush.linearGradient(listOf(cyan500.copy(alpha=0.2f), blue600.copy(alpha=0.2f))),
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(12.dp)
                        ) {
                            Icon(
                                imageVector = RocketIcon,
                                contentDescription = null,
                                tint = cyan500,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Text Content
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Commerce Prime",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "A premium e-commerce experience with advanced features.",
                                color = Color(0xFF64748b), // slate-500
                                fontSize = 14.sp
                            )
                        }

                        Icon(
                            imageVector = ExternalLinkIcon,
                            contentDescription = null,
                            tint = Color(0xFF475569), // slate-600
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Coming Soon Card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                        // Note: Dashed borders require custom drawing, so we'll use a semi-transparent solid border as approximation or draw a custom one
                        .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.White.copy(alpha = 0.05f), CircleShape)
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = AppWindowIcon,
                            contentDescription = null,
                            tint = Color(0xFF64748b), // slate-500
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "More Apps Coming Soon",
                        color = Color(0xFF94a3b8), // slate-400
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "UNDER DEVELOPMENT",
                        color = Color(0xFF475569), // slate-600
                        fontSize = 12.sp,
                        letterSpacing = 2.sp
                    )
                }
            }

            // Footer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.White.copy(alpha = 0.05f))
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "BUILT WITH PASSION BY SOMAY • 2026",
                    color = Color(0xFF475569), // slate-600
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 2.sp
                )
            }
        }

        // Close Button
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(24.dp)
                .background(Color.White.copy(alpha = 0.05f), CircleShape)
        ) {
            Icon(
                imageVector = XIcon,
                contentDescription = "Close Apps Menu",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
