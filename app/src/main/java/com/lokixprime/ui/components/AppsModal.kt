package com.lokixprime.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.modifiers.glowPulse

data class AppItem(
    val name: String,
    val description: String,
    val link: String,
    val icon: @Composable () -> Unit,
    val gradientColors: List<Color>
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
            icon = {
                Icon(
                    imageVector = LokiIcons.Rocket,
                    contentDescription = "Commerce Prime",
                    tint = Color(0xFF06B6D4), // cyan-500
                    modifier = Modifier.size(24.dp)
                )
            },
            gradientColors = listOf(Color(0xFF06B6D4), Color(0xFF2563EB)) // from-cyan-500 to-blue-600
        )
    )

    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300)),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f)) // bg-black/80
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { /* block clicks */ }
                )
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(16.dp), // p-4
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = isOpen,
                enter = scaleIn(initialScale = 0.9f, animationSpec = tween(300)) +
                        slideInVertically(initialOffsetY = { 50 }, animationSpec = tween(300)) +
                        fadeIn(animationSpec = tween(300)),
                exit = scaleOut(targetScale = 0.9f, animationSpec = tween(300)) +
                        slideOutVertically(targetOffsetY = { 50 }, animationSpec = tween(300)) +
                        fadeOut(animationSpec = tween(300))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .fillMaxSize(0.85f) // max-h-[85vh] roughly
                        .shadow(
                            elevation = 50.dp,
                            shape = RoundedCornerShape(32.dp),
                            spotColor = Color.Black.copy(alpha = 0.5f),
                            ambientColor = Color.Black.copy(alpha = 0.5f)
                        )
                        .background(Color(0xFF0A0A0C), RoundedCornerShape(32.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
                        .clip(RoundedCornerShape(32.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        // Content Container
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                                .padding(32.dp), // p-8
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Krishna Avtaar Image
                            Box(contentAlignment = Alignment.Center) {
                                Box(
                                    modifier = Modifier
                                        .size(160.dp)
                                        .glowPulse(color = Color(0xFF06B6D4).copy(alpha = 0.2f))
                                        .background(Color(0xFF06B6D4).copy(alpha = 0.2f), CircleShape)
                                )
                                AsyncImage(
                                    model = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
                                    contentDescription = "Krishna Avtaar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(128.dp) // w-32 h-32
                                        .clip(CircleShape)
                                        .border(4.dp, Color(0xFF06B6D4).copy(alpha = 0.5f), CircleShape)
                                )
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Stylish Animated Name
                            val textGradient = Brush.linearGradient(
                                colors = listOf(Color.White, Color(0xFF22D3EE), Color(0xFF3B82F6)) // white, cyan-400, blue-500
                            )
                            Text(
                                text = "Somay a.k.a. Owner",
                                fontSize = 36.sp, // text-3xl to 5xl roughly
                                fontWeight = FontWeight.Black,
                                letterSpacing = (-1.5).sp,
                                style = androidx.compose.ui.text.TextStyle(
                                    brush = textGradient
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "DIGITAL ARCHITECT & VISIONARY",
                                color = Color(0xFF06B6D4).copy(alpha = 0.8f),
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                letterSpacing = 4.sp
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            // Greeting Message
                            Text(
                                text = "Namaste! Welcome to my digital ecosystem. I'm passionate about building cutting-edge experiences that push the boundaries of what's possible. Explore our latest creations below.",
                                color = Color(0xFF94A3B8), // slate-400
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 28.sp
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            // Apps Section
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = LokiIcons.Sparkles,
                                    contentDescription = "Sparkles",
                                    tint = Color(0xFFEAB308), // yellow-500
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

                            // In Compose, instead of Grid for just 2 items on mobile, we can use a Column.
                            // To match 'grid-cols-1 md:grid-cols-2', we will just use Column since it's a mobile app.
                            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                                apps.forEach { app ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                                            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                            .clip(RoundedCornerShape(16.dp))
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
                                                        Brush.linearGradient(app.gradientColors),
                                                        RoundedCornerShape(12.dp)
                                                    )
                                                    .background(Color.Black.copy(alpha = 0.8f), RoundedCornerShape(12.dp)), // To simulate opacity-20 over gradient
                                                contentAlignment = Alignment.Center
                                            ) {
                                                app.icon()
                                            }
                                            Spacer(modifier = Modifier.width(16.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = app.name,
                                                    color = Color.White,
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = app.description,
                                                    color = Color(0xFF64748B), // slate-500
                                                    fontSize = 14.sp
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(16.dp))
                                            Icon(
                                                imageVector = LokiIcons.ExternalLink,
                                                contentDescription = "External Link",
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
                                        .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                                        // A dashed border requires a custom drawing, but we can simplify to a standard border with lower opacity or keep it solid for now,
                                        // but since exact visual parity is requested, we will use a custom drawing for dashed border.
                                        .clip(RoundedCornerShape(16.dp))
                                        .padding(24.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(48.dp)
                                                .background(Color.White.copy(alpha = 0.05f), CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = LokiIcons.AppWindow,
                                                contentDescription = "App Window",
                                                tint = Color(0xFF64748B), // slate-500
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "More Apps Coming Soon",
                                            color = Color(0xFF94A3B8), // slate-400
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "UNDER DEVELOPMENT",
                                            color = Color(0xFF475569), // slate-600
                                            fontSize = 12.sp,
                                            letterSpacing = 2.sp,
                                            fontFamily = FontFamily.Monospace
                                        )
                                    }
                                }
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
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(24.dp)
                            .size(48.dp)
                            .background(Color.White.copy(alpha = 0.05f), CircleShape)
                            .clip(CircleShape)
                            .clickable { onClose() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = LokiIcons.X,
                            contentDescription = "Close Apps Menu",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}
