package com.lokixprime.ui.components

import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.lokixprime.ui.icons.LokiIcons

@Composable
fun AssistantIntroOverlay(
    isVisible: Boolean,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()

    val bgColor = if (isDark) Color(0xFF0A0A0A) else Color.White
    val textColor = if (isDark) Color.White else Color(0xFF0F172A) // slate-900
    val secondaryTextColor = if (isDark) Color(0xFF717171) else Color(0xFF64748B) // slate-500
    val borderColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0) // slate-200
    val cardBgColor = if (isDark) Color(0xFF161616) else Color(0xFFF8FAFC) // slate-50
    val cardBorderColor = if (isDark) Color.White.copy(alpha = 0.05f) else Color(0xFFF1F5F9) // slate-100

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { 40 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { 40 }),
        modifier = Modifier.zIndex(120f)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = bgColor
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bgColor)
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = textColor
                        )
                    ) {
                        Icon(
                            imageVector = LokiIcons.ChevronDown,
                            contentDescription = "Close",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "AI Assistant",
                        color = textColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.5).sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(borderColor)
                )

                // Scrollable Content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    // Hero Section
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .shadow(elevation = 24.dp, shape = RoundedCornerShape(32.dp))
                                .background(textColor, RoundedCornerShape(32.dp))
                                .rotate(12f)
                                .padding(bottom = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = LokiIcons.Sparkles,
                                contentDescription = null,
                                tint = bgColor,
                                modifier = Modifier
                                    .size(40.dp)
                                    .rotate(-12f)
                            )
                        }

                        Text(
                            text = "Loki X Prime",
                            color = textColor,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Elevate your mobile experience by setting Loki X Prime as your default digital assistant. Access advanced AI capabilities directly from anywhere on your phone.",
                            color = secondaryTextColor,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 24.sp
                        )
                    }

                    // Features List
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        FeatureItem(
                            icon = LokiIcons.Zap,
                            title = "Instant Access",
                            description = "Long-press your home button or swipe from the bottom corner to summon Loki instantly.",
                            textColor = textColor,
                            secondaryTextColor = secondaryTextColor,
                            cardBgColor = cardBgColor,
                            cardBorderColor = cardBorderColor
                        )
                        FeatureItem(
                            icon = LokiIcons.Smartphone,
                            title = "Deep Integration",
                            description = "Replace your system's default assistant with a smarter, highly capable AI companion.",
                            textColor = textColor,
                            secondaryTextColor = secondaryTextColor,
                            cardBgColor = cardBgColor,
                            cardBorderColor = cardBorderColor
                        )
                        FeatureItem(
                            icon = LokiIcons.CheckCircle,
                            title = "Ready to Assist",
                            description = "Always available to answer questions, generate content, or help with tasks on the go.",
                            textColor = textColor,
                            secondaryTextColor = secondaryTextColor,
                            cardBgColor = cardBgColor,
                            cardBorderColor = cardBorderColor
                        )
                    }
                }

                // Bottom Action
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bgColor)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(borderColor)
                            .align(Alignment.TopCenter)
                    )

                    Button(
                        onClick = {
                            try {
                                context.startActivity(Intent(Settings.ACTION_VOICE_INPUT_SETTINGS))
                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    "Could not open assistant settings. Please try manually in your device settings.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                            .height(56.dp)
                            .shadow(elevation = 8.dp, shape = CircleShape),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = textColor,
                            contentColor = bgColor
                        ),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Set Loki X Prime as default AI assistant",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FeatureItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    textColor: Color,
    secondaryTextColor: Color,
    cardBgColor: Color,
    cardBorderColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(cardBgColor)
            .border(border = BorderStroke(1.dp, cardBorderColor), shape = RoundedCornerShape(16.dp))
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )
        Column {
            Text(
                text = title,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = description,
                color = secondaryTextColor,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}
