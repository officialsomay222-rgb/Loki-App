package com.lokixprime.ui.components

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lokixprime.ui.icons.LokiIcons
import com.lokixprime.ui.modifiers.glassmorphism
import com.lokixprime.ui.theme.*

@Composable
fun ChatInputBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
    isAwakenedMode: Boolean
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        val shape = RoundedCornerShape(24.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .let {
                    if (isAwakenedMode) {
                        it.glassmorphism(
                            shape = shape,
                            backgroundColor = GlassBackgroundDark,
                            borderColor = if (text.isNotEmpty()) InputFocusedBorderDark else GlassBorderDark
                        )
                    } else {
                        it.background(InputBackgroundDark, shape)
                          .border(1.dp, if (text.isNotEmpty()) InputFocusedBorderDark else InputBorderDark, shape)
                    }
                }
                .padding(horizontal = 4.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Text Field
            TextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 4.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = LokiCyan,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                placeholder = {
                    Text(
                        text = "Message Loki...",
                        color = Color.Gray,
                        fontFamily = Inter
                    )
                },
                maxLines = 5,
                textStyle = MaterialTheme.typography.bodyLarge
            )

            // Right side icons (Mic vs Send)
            AnimatedContent(
                targetState = text.isEmpty(),
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "send_mic_transition"
            ) { isEmpty ->
                if (isEmpty) {
                    IconButton(onClick = {
                        Toast.makeText(context, "Voice Recording API coming soon", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = LokiIcons.Mic,
                            contentDescription = "Voice",
                            tint = Color.Gray
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(if (isAwakenedMode) LokiCyan.copy(alpha=0.2f) else Color.White.copy(alpha = 0.1f))
                            .clickable { onSendClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = LokiIcons.Send,
                            contentDescription = "Send",
                            tint = if (isAwakenedMode) LokiCyan else Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
