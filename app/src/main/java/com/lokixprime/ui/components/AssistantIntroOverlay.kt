package com.lokixprime.ui.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.lokixprime.ui.icons.CheckCircle
import com.lokixprime.ui.icons.Smartphone
import com.lokixprime.ui.icons.LokiIcons.ChevronDown
import com.lokixprime.ui.icons.LokiIcons.Sparkles
import com.lokixprime.ui.icons.LokiIcons.Zap

@Composable
fun AssistantIntroOverlay(
    isVisible: Boolean,
    onClose: () -> Unit,
    isAwakenedMode: Boolean = false
) {
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme() || isAwakenedMode

    val bgColor = if (isDark) Color(0xFF0A0A0A) else Color.White
    val textColor = if (isDark) Color.White else Color(0xFF0F172A) // slate-900
    val mutedTextColor = if (isDark) Color(0xFF717171) else Color(0xFF64748B) // slate-500
    val boxBgColor = if (isDark) Color(0xFF161616) else Color(0xFFF8FAFC) // slate-50
    val borderColor = if (isDark) Color.White.copy(alpha = 0.05f) else Color(0xFFF1F5F9) // slate-100
    val headerBorderColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0) // slate-200

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { 40 }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { 40 }) + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bgColor)
                    .border(width = 1.dp, color = headerBorderColor, shape = RoundedCornerShape(0.dp))
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onClose,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Transparent)
                ) {
                    Icon(
                        imageVector = ChevronDown,
                        contentDescription = "Close",
                        tint = textColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "AI Assistant",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    letterSpacing = (-0.5).sp
                )
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                // Top section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .rotate(12f)
                            .shadow(elevation = 20.dp, shape = RoundedCornerShape(32.dp))
                            .background(
                                color = if (isDark) Color.White else Color(0xFF0F172A),
                                shape = RoundedCornerShape(32.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Sparkles,
                            contentDescription = "Sparkles",
                            tint = if (isDark) Color.Black else Color.White,
                            modifier = Modifier
                                .size(40.dp)
                                .rotate(-12f)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Loki X Prime",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Elevate your mobile experience by setting Loki X Prime as your default digital assistant. Access advanced AI capabilities directly from anywhere on your phone.",
                        fontSize = 16.sp,
                        color = mutedTextColor,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Features
                FeatureItem(
                    icon = Zap,
                    title = "Instant Access",
                    description = "Long-press your home button or swipe from the bottom corner to summon Loki instantly.",
                    textColor = textColor,
                    mutedTextColor = mutedTextColor,
                    boxBgColor = boxBgColor,
                    borderColor = borderColor
                )
                Spacer(modifier = Modifier.height(16.dp))
                FeatureItem(
                    icon = Smartphone,
                    title = "Deep Integration",
                    description = "Replace your system's default assistant with a smarter, highly capable AI companion.",
                    textColor = textColor,
                    mutedTextColor = mutedTextColor,
                    boxBgColor = boxBgColor,
                    borderColor = borderColor
                )
                Spacer(modifier = Modifier.height(16.dp))
                FeatureItem(
                    icon = CheckCircle,
                    title = "Ready to Assist",
                    description = "Always available to answer questions, generate content, or help with tasks on the go.",
                    textColor = textColor,
                    mutedTextColor = mutedTextColor,
                    boxBgColor = boxBgColor,
                    borderColor = borderColor
                )
            }

            // Footer Action Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bgColor)
                    .border(width = 1.dp, color = headerBorderColor, shape = RoundedCornerShape(0.dp))
                    .padding(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 10.dp, shape = CircleShape)
                        .clip(CircleShape)
                        .background(if (isDark) Color.White else Color(0xFF0F172A))
                        .clickable {
                            try {
                                val intent = Intent(android.provider.Settings.ACTION_VOICE_INPUT_SETTINGS)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                context.startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                Toast.makeText(
                                    context,
                                    "Could not open assistant settings. Please try manually in your device settings.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Set Loki X Prime as default AI assistant",
                        color = if (isDark) Color(0xFF0F172A) else Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
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
    mutedTextColor: Color,
    boxBgColor: Color,
    borderColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(boxBgColor)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier
                .size(24.dp)
                .padding(top = 2.dp) // Slight adjustment to align with text
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                color = mutedTextColor,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}