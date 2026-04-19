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
import androidx.compose.animation.animateContentSize
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.viewmodel.ChatMessage
import com.lokixprime.ui.theme.*
import com.lokixprime.ui.modifiers.glassmorphism

@Composable
fun MessageBubble(
    message: ChatMessage,
    isAwakenedMode: Boolean = false,
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
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .animateContentSize(),
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {

            if (!isUser) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(LokiCyan.copy(alpha = 0.2f))
                        .border(1.dp, LokiCyan.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "∞",
                        color = LokiCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
            ) {
                Text(
                    text = if (isUser) "COMMANDER" else "LOKI PRIME",
                    color = Color.Gray,
                    fontSize = 10.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp, end = 4.dp)
                )

                Surface(
                    color = Color.Transparent,
                    shape = bubbleShape,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .let {
                            if (isAwakenedMode && !isUser) {
                                it.glassmorphism(shape = bubbleShape)
                            } else {
                                it.background(bubbleBg, bubbleShape).border(1.dp, bubbleBorder, bubbleShape)
                            }
                        }
                ) {
                    Text(
                        text = message.content,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = Inter,
                        lineHeight = 24.sp
                    )
                }
            }

            if (isUser) {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.DarkGray)
                        .border(1.dp, Color.Gray, CircleShape)
                ) {
                     AsyncImage(
                        model = "https://i.ibb.co/ns3LTFwp/Picsart-26-02-28-11-29-26-443.jpg",
                        contentDescription = "User",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
