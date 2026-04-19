package com.lokixprime.ui.components

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.modifiers.glassmorphism
import com.lokixprime.ui.theme.*

@Composable
fun ChatInputBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
    isAwakenedMode: Boolean,
    modelMode: String = "fast",
    onModelModeChange: (String) -> Unit = {}
) {
    val context = LocalContext.current
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        val outerShape = RoundedCornerShape(32.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .let {
                    if (isAwakenedMode) {
                        it.padding(2.dp) // Space for the RGB border if we had a custom modifier for it
                    } else it
                }
        ) {
            // Main Input Container
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .let {
                        if (isAwakenedMode) {
                            it.glassmorphism(
                                shape = outerShape,
                                backgroundColor = Color(0xFF050505).copy(alpha = 0.9f)
                            ).border(1.dp, Color(0xFF00F2FF).copy(alpha = 0.3f), outerShape)
                        } else {
                            it.background(Color.White.copy(alpha = 0.05f), outerShape)
                              .border(1.dp, Color.White.copy(alpha = 0.1f), outerShape)
                        }
                    }
                    .padding(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    // Text Field
                    TextField(
                        value = text,
                        onValueChange = onTextChange,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = if (isAwakenedMode) Color(0xFF00F2FF) else Color.White,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        placeholder = {
                            Text(
                                text = "Ask AI...",
                                color = Color.Gray,
                                fontFamily = Inter,
                                fontSize = 16.sp
                            )
                        },
                        maxLines = 6,
                        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
                    )

                    // Bottom Actions Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Plus Icon
                        IconButton(onClick = { /* File Picker */ }) {
                            Icon(
                                imageVector = LokiIcons.Plus,
                                contentDescription = "Actions",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Model Selector
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                .clickable { /* Toggle Model Menu */ }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = modelMode.replaceFirstChar { it.uppercase() },
                                    color = Color.LightGray,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = Inter
                                )
                                Icon(
                                    imageVector = LokiIcons.ChevronDown,
                                    contentDescription = null,
                                    tint = Color.Gray,
                                    modifier = Modifier.size(14.dp).padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }

                // Mic / Send / Stop Button
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp, end = 8.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            if (text.isNotEmpty()) {
                                if (isAwakenedMode) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.1f)
                            } else Color.Transparent
                        )
                        .let {
                            if (text.isEmpty()) it.border(1.dp, Color.White.copy(alpha = 0.1f), CircleShape) else it
                        }
                        .clickable {
                            if (text.isNotEmpty()) onSendClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (text.isEmpty()) {
                        Icon(
                            imageVector = LokiIcons.Mic,
                            contentDescription = "Voice",
                            tint = Color.LightGray,
                            modifier = Modifier.size(22.dp)
                        )
                    } else {
                        Icon(
                            imageVector = LokiIcons.Send,
                            contentDescription = "Send",
                            tint = if (isAwakenedMode) Color(0xFF00F2FF) else Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
