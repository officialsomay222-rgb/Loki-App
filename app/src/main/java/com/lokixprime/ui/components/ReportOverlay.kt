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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
    val borderColor = if (isDark) Color(0x1AFFFFFF) else Color(0xFFE2E8F0)
    val titleColor = if (isDark) Color.White else Color(0xFF0F172A)
    val textColor = if (isDark) Color(0xFF717171) else Color(0xFF64748B)
    val inputBgColor = if (isDark) Color(0xFF161616) else Color(0xFFF8FAFC)

    var reportText by remember { mutableStateOf("") }

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
                    text = "Report a Problem",
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
                Text(
                    text = "Describe the issue you're experiencing. Our team will look into it as soon as possible.",
                    fontSize = 14.sp,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(24.dp))

                BasicTextField(
                    value = reportText,
                    onValueChange = { reportText = it },
                    textStyle = TextStyle(
                        color = titleColor,
                        fontSize = 14.sp
                    ),
                    cursorBrush = SolidColor(titleColor),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(192.dp)
                                .background(inputBgColor, RoundedCornerShape(16.dp))
                                .border(1.dp, borderColor, RoundedCornerShape(16.dp))
                                .padding(16.dp)
                        ) {
                            if (reportText.isEmpty()) {
                                Text(
                                    text = "Type your message here...",
                                    color = textColor.copy(alpha = 0.5f),
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        Toast.makeText(context, "Thank you for your report!", Toast.LENGTH_SHORT).show()
                        reportText = ""
                        onClose()
                    },
                    enabled = reportText.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563EB),
                        disabledContainerColor = Color(0xFF2563EB).copy(alpha = 0.5f)
                    )
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
