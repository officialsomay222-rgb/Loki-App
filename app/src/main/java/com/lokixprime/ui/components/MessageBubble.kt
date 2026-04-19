package com.lokixprime.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.viewmodel.ChatMessage
import com.lokixprime.ui.theme.*

@Composable
fun MessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    val isUser = message.isUser

    val alignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleBg = if (isUser) BubbleUserBg else BubbleModelBg
    val bubbleBorder = if (isUser) BubbleUserBorder else BubbleModelBorder

    val bubbleShape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = if (isUser) 16.dp else 4.dp,
        bottomEnd = if (isUser) 4.dp else 16.dp
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        contentAlignment = alignment
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.85f),
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {

            if (!isUser) {
                AvatarIcon(initial = "L", modifier = Modifier.padding(end = 8.dp))
            }

            Column(
                horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
            ) {
                Text(
                    text = if (isUser) "COMMANDER" else "LOKI",
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp, end = 4.dp)
                )

                Surface(
                    color = bubbleBg,
                    shape = bubbleShape,
                    border = androidx.compose.foundation.BorderStroke(1.dp, bubbleBorder),
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = message.content,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            if (isUser) {
                AvatarIcon(initial = "OP", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Composable
fun AvatarIcon(initial: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color.DarkGray)
            .border(1.dp, Color.Gray, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
