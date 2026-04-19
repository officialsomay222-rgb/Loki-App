package com.lokixprime.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokixprime.data.db.entity.MessageEntity
import com.lokixprime.ui.theme.*
import com.lokixprime.ui.modifiers.glassmorphism
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.TextView
import io.noties.markwon.Markwon
import io.noties.markwon.image.coil.CoilImagesPlugin
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageBubble(
    message: MessageEntity,
    commanderName: String = "COMMANDER",
    isAwakenedMode: Boolean = false,
    modifier: Modifier = Modifier
) {
    val isUser = message.role == "user"

    val alignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleBg = if (isUser) {
        if (isAwakenedMode) Color.White.copy(alpha = 0.1f) else BubbleUserBg
    } else {
        if (isAwakenedMode) Color.Black.copy(alpha = 0.85f) else BubbleModelBg
    }
    val bubbleBorder = if (isUser) {
        if (isAwakenedMode) Color.White.copy(alpha = 0.2f) else BubbleUserBorder
    } else {
        if (isAwakenedMode) Color(0xFF00F2FF).copy(alpha = 0.25f) else BubbleModelBorder
    }

    val bubbleShape = RoundedCornerShape(
        topStart = if (isUser) 24.dp else 4.dp,
        topEnd = if (isUser) 4.dp else 24.dp,
        bottomStart = 24.dp,
        bottomEnd = 24.dp
    )

    val enterTransition = remember {
        fadeIn(animationSpec = tween(600)) + slideInVertically(
            initialOffsetY = { 20 },
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessMedium)
        )
    }

    AnimatedVisibility(
        visible = true, // Triggered when added to list
        enter = enterTransition
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            contentAlignment = alignment
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
            ) {
                // Label Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
                    modifier = Modifier.padding(bottom = 4.dp, start = 8.dp, end = 8.dp)
                ) {
                    if (!isUser) {
                        HeaderInfinityLogo(modifier = Modifier.size(24.dp).padding(end = 6.dp))
                    }

                    Text(
                        text = if (isUser) commanderName.uppercase() else "LOKI PRIME",
                        color = if (isAwakenedMode && !isUser) Color(0xFF00F2FF) else Color.Gray,
                        fontSize = 10.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                    Text(
                        text = sdf.format(Date(message.timestamp)),
                        color = Color.Gray.copy(alpha = 0.6f),
                        fontSize = 8.sp,
                        fontFamily = JetBrainsMono
                    )

                    if (isUser) {
                        Box(
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .size(20.dp)
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

                // Message Content
                Surface(
                    color = Color.Transparent,
                    shape = bubbleShape,
                    modifier = Modifier
                        .let {
                            if (isAwakenedMode) {
                                if (isUser) {
                                    it.background(bubbleBg, bubbleShape).border(1.dp, bubbleBorder, bubbleShape)
                                } else {
                                    it.glassmorphism(shape = bubbleShape, color = Color(0xFF050508).copy(alpha = 0.85f))
                                        .border(1.dp, Color(0xFF00F2FF).copy(alpha = 0.25f), bubbleShape)
                                }
                            } else {
                                it.background(bubbleBg, bubbleShape).border(1.dp, bubbleBorder, bubbleShape)
                            }
                        }
                ) {
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                        val context = androidx.compose.ui.platform.LocalContext.current
                        val markwon = androidx.compose.runtime.remember(context) {
                            Markwon.builder(context)
                                .usePlugin(CoilImagesPlugin.create(context))
                                .build()
                        }
                        AndroidView(
                            factory = { ctx ->
                                TextView(ctx).apply {
                                    setTextColor(if (isUser) android.graphics.Color.WHITE else android.graphics.Color.WHITE)
                                    setLineSpacing(0f, 1.4f)
                                    textSize = 15f
                                }
                            },
                            update = { textView ->
                                markwon.setMarkdown(textView, message.content)
                            }
                        )
                    }
                }
            }
        }
    }
}
