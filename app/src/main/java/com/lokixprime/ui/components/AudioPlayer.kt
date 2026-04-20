package com.lokixprime.ui.components

import android.media.MediaPlayer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin
import com.lokixprime.ui.icons.Play
import com.lokixprime.ui.icons.Pause

@Composable
fun AudioPlayer(
    url: String,
    autoPlay: Boolean = false,
    onPlay: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }
    var duration by remember { mutableIntStateOf(0) }
    var currentTime by remember { mutableIntStateOf(0) }

    val mediaPlayer = remember { MediaPlayer() }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    LaunchedEffect(url) {
        mediaPlayer.reset()
        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.setOnPreparedListener { mp ->
                duration = mp.duration / 1000
                if (autoPlay && !isPlaying) {
                    mp.start()
                    isPlaying = true
                    onPlay?.invoke()
                }
            }
            mediaPlayer.setOnCompletionListener {
                isPlaying = false
                currentTime = duration
            }
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            if (mediaPlayer.isPlaying) {
                currentTime = mediaPlayer.currentPosition / 1000
            }
            delay(50)
        }
    }

    val bars = remember(url) {
        List(40) { i ->
            val valFloat = sin(i * 0.5) * 30 + cos(i * 0.2) * 20 + 50
            max(20.0, min(100.0, valFloat)).toFloat()
        }
    }

    val togglePlay = {
        if (isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
            onPlay?.invoke()
        }
        isPlaying = !isPlaying
    }

    val progressPercentage = if (duration > 0) (currentTime.toFloat() / duration.toFloat()) * 100f else 0f

    fun formatTime(time: Int): String {
        if (time < 0) return "00:00"
        val minutes = time / 60
        val seconds = time % 60
        return String.format(Locale.US, "%02d:%02d", minutes, seconds)
    }

    val isDarkTheme = isSystemInDarkTheme()
    val bgColor = if (isDarkTheme) Color.Black.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.4f)

    Row(
        modifier = modifier
            .width(300.dp)
            .background(bgColor, RoundedCornerShape(8.dp))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Play/Pause Button
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(Color.White.copy(alpha = 0.2f), CircleShape)
                .border(1.dp, Color.White.copy(alpha = 0.3f), CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = togglePlay
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isPlaying) Pause else Play,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier.size(18.dp).let { if (!isPlaying) it.padding(start = 2.dp) else it }
            )
        }

        // Waveform
        Row(
            modifier = Modifier
                .weight(1f)
                .height(32.dp)
                .pointerInput(duration) {
                    detectTapGestures { offset ->
                        if (duration == 0) return@detectTapGestures
                        val x = offset.x
                        val percentage = x / size.width
                        val newTime = (percentage * duration).toInt()
                        mediaPlayer.seekTo(newTime * 1000)
                        currentTime = newTime
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            bars.forEachIndexed { i, heightPercent ->
                val isPlayed = (i.toFloat() / bars.size) * 100f <= progressPercentage
                val targetHeight = heightPercent / 100f
                val animatedHeight by animateFloatAsState(
                    targetValue = targetHeight,
                    animationSpec = tween(durationMillis = 150),
                    label = "height"
                )

                val barOpacity = if (isPlaying && !isPlayed) 0.6f else 1f
                val barColor = if (isPlayed) Color.White else Color.White.copy(alpha = 0.2f)

                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .fillMaxHeight(animatedHeight)
                        .clip(CircleShape)
                        .background(barColor.copy(alpha = barOpacity))
                        .drawBehind {
                            if (isPlayed) {
                                val paint = Paint().apply {
                                    color = Color.White.copy(alpha = 0.6f)
                                }
                                drawIntoCanvas { canvas ->
                                    val frameworkPaint = paint.asFrameworkPaint()
                                    frameworkPaint.setShadowLayer(
                                        8.dp.toPx(),
                                        0f,
                                        0f,
                                        Color.White.copy(alpha = 0.6f).toArgb()
                                    )
                                    canvas.drawRoundRect(
                                        0f,
                                        0f,
                                        size.width,
                                        size.height,
                                        size.width / 2,
                                        size.height / 2,
                                        paint
                                    )
                                }
                            }
                        }
                )
            }
        }

        // Timer Text
        Text(
            text = formatTime(if (currentTime > 0) currentTime else duration),
            color = Color(0xFFCBD5E1), // slate-300
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp, // tracking-wider
            modifier = Modifier.width(40.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Right
        )
    }
}
