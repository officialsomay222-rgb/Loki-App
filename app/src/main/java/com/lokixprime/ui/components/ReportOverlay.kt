package com.lokixprime.ui.components

import android.widget.Toast
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
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.icons.LokiIcons

@Composable
fun ReportOverlay(
    visible: Boolean,
    onClose: () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val context = LocalContext.current

    val bgColor = if (isDark) Color(0xFF0A0A0A) else Color.White
    val borderColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0)
    val headerTextColor = if (isDark) Color.White else Color(0xFF0F172A)
    val descTextColor = if (isDark) Color(0xFF717171) else Color(0xFF64748B)

    var reportText by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(300)) + slideInVertically(
            initialOffsetY = { 50 },
            animationSpec = tween(300)
        ),
        exit = fadeOut(tween(300)) + slideOutVertically(
            targetOffsetY = { 50 },
            animationSpec = tween(300)
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {} // block clicks
                )
        ) {
            // Header sticky top
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Transparent) // placeholder for bottom border
                    .background(bgColor)
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val closeInteractionSource = remember { MutableInteractionSource() }
                val isClosePressed by closeInteractionSource.collectIsPressedAsState()

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .scale(if (isClosePressed) 0.9f else 1f)
                        .clip(CircleShape)
                        .background(if (isClosePressed) Color.White.copy(alpha = 0.1f) else Color.Transparent)
                        .clickable(
                            interactionSource = closeInteractionSource,
                            indication = null,
                            onClick = {
                                reportText = ""
                                onClose()
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = LokiIcons.ChevronDown,
                        contentDescription = "Close",
                        tint = headerTextColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = "Report a Problem",
                    color = headerTextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.5).sp, // tracking-tight
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // Bottom border of header
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(borderColor))

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                Text(
                    text = "Describe the issue you're experiencing. Our team will look into it as soon as possible.",
                    color = descTextColor,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                val textAreaBg = if (isDark) Color(0xFF161616) else Color(0xFFF8FAFC) // bg-[#161616] or bg-slate-50
                val textAreaBorder = if (isFocused) {
                    if (isDark) Color.White.copy(alpha = 0.2f) else Color(0xFFCBD5E1) // border-white/20 or border-slate-300
                } else {
                    if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE2E8F0) // border-white/10 or border-slate-200
                }
                val textAreaTextColor = if (isDark) Color.White else Color(0xFF0F172A)

                BasicTextField(
                    value = reportText,
                    onValueChange = { reportText = it },
                    textStyle = TextStyle(
                        color = textAreaTextColor,
                        fontSize = 14.sp
                    ),
                    cursorBrush = SolidColor(if (isDark) Color.White else Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(192.dp) // h-48
                        .onFocusChanged { isFocused = it.isFocused }
                        .background(textAreaBg, RoundedCornerShape(16.dp)) // rounded-2xl
                        .border(1.dp, textAreaBorder, RoundedCornerShape(16.dp))
                        .padding(16.dp), // p-4
                    decorationBox = { innerTextField ->
                        Box {
                            if (reportText.isEmpty()) {
                                Text(
                                    text = "Type your message here...",
                                    color = descTextColor,
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                val submitInteractionSource = remember { MutableInteractionSource() }
                val isSubmitPressed by submitInteractionSource.collectIsPressedAsState()
                val isSubmitEnabled = reportText.trim().isNotEmpty()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(if (isSubmitPressed && isSubmitEnabled) 0.98f else 1f)
                        .shadow(
                            elevation = if (isSubmitEnabled) 20.dp else 0.dp,
                            shape = CircleShape,
                            spotColor = Color(0x332563EB), // shadow-blue-600/20
                            ambientColor = Color(0x332563EB)
                        )
                        .background(
                            color = if (isSubmitEnabled) {
                                if (isSubmitPressed) Color(0xFF2563EB) else Color(0xFF2563EB) // hover bg-blue-600 logic approx
                            } else {
                                Color(0xFF2563EB).copy(alpha = 0.5f) // disabled:opacity-50
                            },
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .clickable(
                            interactionSource = submitInteractionSource,
                            indication = null,
                            enabled = isSubmitEnabled,
                            onClick = {
                                Toast.makeText(context, "Thank you for your report!", Toast.LENGTH_SHORT).show()
                                reportText = ""
                                onClose()
                            }
                        )
                        .padding(vertical = 16.dp), // py-4
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Submit Report",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
