package com.lokixprime.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.theme.Typography
import com.lokixprime.ui.theme.LokiCyan
import com.lokixprime.ui.modifiers.glassmorphism
import com.lokixprime.ui.modifiers.glowPulse

data class AppItem(
    val name: String,
    val description: String,
    val link: String,
    val iconColor: Color,
    val gradientColors: List<Color>
)

@Composable
fun AppsModal(
    isOpen: Boolean,
    onClose: () -> Unit,
    commanderName: String
) {
    val context = LocalContext.current

    val apps = remember {
        listOf(
            AppItem(
                name = "Commerce Prime",
                description = "A premium e-commerce experience with advanced features.",
                link = "https://commerce-prime.vercel.app/",
                iconColor = Color(0xFF06B6D4), // cyan-500
                gradientColors = listOf(Color(0xFF06B6D4), Color(0xFF2563EB)) // cyan-500 to blue-600
            )
        )
    }

    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
                .blur(radius = 16.dp)
                .clickable(enabled = false) {}, // Intercept clicks
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = isOpen,
                enter = slideInVertically(
                    initialOffsetY = { 20 },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300)),
                exit = slideOutVertically(
                    targetOffsetY = { 20 },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .fillMaxHeight(0.85f)
                        .background(
                            color = Color(0xFF0A0A0C),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(32.dp)
                        )
                        // .shadow(elevation = 50.dp, spotColor = Color.Black.copy(alpha = 0.5f)) -> custom drop shadow in CSS, compose shadow is simple
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        // Header / Close button
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            IconButton(
                                onClick = onClose,
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        color = Color.White.copy(alpha = 0.05f),
                                        shape = CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = LokiIcons.X,
                                    contentDescription = "Close Apps Menu",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        // Content
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Krishna Avtaar Image
                            Box(
                                modifier = Modifier
                                    .padding(bottom = 32.dp)
                                    .glowPulse(color = Color(0xFF06B6D4))
                            ) {
                                AsyncImage(
                                    model = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
                                    contentDescription = "Krishna Avtaar",
                                    modifier = Modifier
                                        .size(128.dp)
                                        .clip(CircleShape)
                                        .border(
                                            width = 4.dp,
                                            color = Color(0xFF06B6D4).copy(alpha = 0.5f),
                                            shape = CircleShape
                                        ),
                                    contentScale = ContentScale.Crop
                                )
                            }

                            // Stylish Animated Name
                            Text(
                                text = "Somay a.k.a. Owner",
                                fontFamily = Typography.titleLarge.fontFamily,
                                fontWeight = FontWeight.Black,
                                fontSize = 36.sp, // adjust for mobile
                                color = Color(0xFF06B6D4), // simple fallback, ideally a brush text
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Text(
                                text = "DIGITAL ARCHITECT & VISIONARY",
                                fontFamily = Typography.labelSmall.fontFamily,
                                fontSize = 12.sp,
                                color = Color(0xFF06B6D4).copy(alpha = 0.8f),
                                letterSpacing = 3.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 32.dp)
                            )

                            // Greeting Message
                            Text(
                                text = "Namaste! Welcome to my digital ecosystem. I'm passionate about building cutting-edge experiences that push the boundaries of what's possible. Explore our latest creations below.",
                                fontFamily = Typography.bodyLarge.fontFamily,
                                fontSize = 16.sp,
                                color = Color(0xFF94A3B8), // slate-400
                                textAlign = TextAlign.Center,
                                lineHeight = 24.sp,
                                modifier = Modifier.padding(bottom = 32.dp)
                            )

                            // Apps Section
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 24.dp)
                            ) {
                                Icon(
                                    imageVector = LokiIcons.Sparkles,
                                    contentDescription = null,
                                    tint = Color(0xFFEAB308), // yellow-500
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(end = 12.dp)
                                )
                                Text(
                                    text = "Try Our Another Apps",
                                    fontFamily = Typography.titleMedium.fontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }

                            // App Cards
                            apps.forEach { app ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 24.dp)
                                        .background(
                                            color = Color.White.copy(alpha = 0.05f),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .border(
                                            width = 1.dp,
                                            color = Color.White.copy(alpha = 0.1f),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .clickable {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(app.link))
                                            context.startActivity(intent)
                                        }
                                        .padding(24.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(48.dp)
                                                .background(
                                                    brush = Brush.linearGradient(app.gradientColors),
                                                    alpha = 0.2f,
                                                    shape = RoundedCornerShape(12.dp)
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = LokiIcons.Rocket,
                                                contentDescription = null,
                                                tint = app.iconColor,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }

                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(horizontal = 16.dp)
                                        ) {
                                            Text(
                                                text = app.name,
                                                fontFamily = Typography.titleMedium.fontFamily,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp,
                                                color = Color.White,
                                                modifier = Modifier.padding(bottom = 4.dp)
                                            )
                                            Text(
                                                text = app.description,
                                                fontFamily = Typography.bodyMedium.fontFamily,
                                                fontSize = 14.sp,
                                                color = Color(0xFF64748B) // slate-500
                                            )
                                        }

                                        Icon(
                                            imageVector = LokiIcons.ExternalLink,
                                            contentDescription = null,
                                            tint = Color(0xFF475569), // slate-600
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                }
                            }

                            // Coming Soon Card
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 32.dp)
                                    .background(
                                        color = Color.White.copy(alpha = 0.05f),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    // dash border is hard in compose, solid for now
                                    .border(
                                        width = 1.dp,
                                        color = Color.White.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .padding(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(
                                                color = Color.White.copy(alpha = 0.05f),
                                                shape = CircleShape
                                            )
                                            .padding(bottom = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = LokiIcons.AppWindow,
                                            contentDescription = null,
                                            tint = Color(0xFF64748B), // slate-500
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Text(
                                        text = "More Apps Coming Soon",
                                        fontFamily = Typography.titleMedium.fontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color(0xFF94A3B8), // slate-400
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                    Text(
                                        text = "UNDER DEVELOPMENT",
                                        fontFamily = Typography.labelSmall.fontFamily,
                                        fontSize = 12.sp,
                                        color = Color(0xFF475569), // slate-600
                                        letterSpacing = 2.sp
                                    )
                                }
                            }
                        }

                        // Footer
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.White.copy(alpha = 0.05f)
                                )
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "BUILT WITH PASSION BY SOMAY • 2026",
                                fontFamily = Typography.labelSmall.fontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = Color(0xFF475569), // slate-600
                                letterSpacing = 2.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
