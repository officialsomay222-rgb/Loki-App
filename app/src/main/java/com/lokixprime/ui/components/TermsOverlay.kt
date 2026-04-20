package com.lokixprime.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.icons.LokiIcons

@Composable
fun TermsOverlay(
    visible: Boolean,
    onClose: () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    val bgColor = if (isDark) Color(0xFF0A0A0A) else Color.White
    val borderColor = if (isDark) Color(0x1AFFFFFF) else Color(0xFFE2E8F0)
    val titleColor = if (isDark) Color.White else Color(0xFF0F172A)
    val textColor = if (isDark) Color(0xFF717171) else Color(0xFF64748B)

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { 40 }, animationSpec = tween(200)) + fadeIn(tween(200)),
        exit = slideOutVertically(targetOffsetY = { 40 }, animationSpec = tween(200)) + fadeOut(tween(200)),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {}
                )
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bgColor)
                    .border(1.dp, borderColor)
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .clickable { onClose() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = LokiIcons.ChevronDown,
                        contentDescription = "Close",
                        tint = titleColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = "Terms of Use",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor,
                    modifier = Modifier.padding(start = 16.dp),
                    letterSpacing = (-0.5).sp
                )
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Section(
                    title = "1. Acceptance of Terms",
                    content = "By using Loki Prime X, you agree to these terms. If you do not agree, please do not use the service.",
                    titleColor = titleColor,
                    textColor = textColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                Section(
                    title = "2. Use of Service",
                    content = "You agree to use the service for lawful purposes only. You are responsible for all content you generate or share.",
                    titleColor = titleColor,
                    textColor = textColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                Section(
                    title = "3. Privacy",
                    content = "Your privacy is important to us. Please review our Privacy Policy to understand how we handle your data.",
                    titleColor = titleColor,
                    textColor = textColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                Section(
                    title = "4. AI Disclaimer",
                    content = "Loki Prime X uses advanced AI models. Responses may be inaccurate, biased, or incomplete. Always verify important information.",
                    titleColor = titleColor,
                    textColor = textColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                Section(
                    title = "5. Modifications",
                    content = "We reserve the right to modify these terms at any time. Continued use of the service constitutes acceptance of new terms.",
                    titleColor = titleColor,
                    textColor = textColor
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "LAST UPDATED: MARCH 2026",
                    fontSize = 10.sp,
                    color = textColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    letterSpacing = 1.5.sp
                )
            }
        }
    }
}

@Composable
private fun Section(title: String, content: String, titleColor: Color, textColor: Color) {
    Column {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = titleColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            fontSize = 14.sp,
            color = textColor,
            lineHeight = 22.sp
        )
    }
}
