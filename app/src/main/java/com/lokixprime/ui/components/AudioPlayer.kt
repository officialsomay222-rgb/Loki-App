package com.lokixprime.ui.components

import android.media.MediaPlayer
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokixprime.ui.theme.JetBrainsMono
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun AudioPlayer(
    url: String,
    autoPlay: Boolean = false,
    onPlay: (() -> Unit)? = null
) {
    var isPlaying by remember { mutableStateOf(false) }
    var duration by remember { mutableStateOf(0) }
    var currentTime by remember { mutableStateOf(0) }
    var isPrepared by remember { mutableStateOf(false) }

    val mediaPlayer = remember(url) { MediaPlayer() }

    // Waveform heights
    val bars = remember(url) {
        List(40) { i ->
            val valHeight = sin(i * 0.5) * 30 + cos(i * 0.2) * 20 + 50
            valHeight.coerceIn(20.0, 100.0)
        }
    }

    DisposableEffect(url) {
        isPrepared = false
        isPlaying = false
        currentTime = 0
        duration = 0

        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(url)
            mediaPlayer.setOnPreparedListener { mp ->
                isPrepared = true
                duration = mp.duration / 1000
                if (autoPlay) {
                    mp.start()
                    isPlaying = true
                    onPlay?.invoke()
                }
            }
            mediaPlayer.setOnCompletionListener {
                isPlaying = false
                currentTime = 0
            }
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        onDispose {
            try {
                if (mediaPlayer.isPlaying) mediaPlayer.stop()
                mediaPlayer.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    LaunchedEffect(isPlaying, isPrepared) {
        while (isActive && isPlaying && isPrepared) {
            try {
                if (mediaPlayer.isPlaying) {
                    currentTime = mediaPlayer.currentPosition / 1000
                }
            } catch (e: Exception) {
                // Ignore illegal state
            }
            delay(100)
        }
    }

    fun togglePlay() {
        if (!isPrepared) return
        try {
            if (isPlaying) {
                mediaPlayer.pause()
            } else {
                mediaPlayer.start()
                onPlay?.invoke()
            }
            isPlaying = !isPlaying
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun formatTime(timeSeconds: Int): String {
        val minutes = timeSeconds / 60
        val seconds = timeSeconds % 60
        return "%02d:%02d".format(minutes, seconds)
    }

    val progressPercentage = if (duration > 0) (currentTime.toFloat() / duration.toFloat()) * 100f else 0f

    val PlayIcon = remember {
        ImageVector.Builder(
            name = "Play",
            defaultWidth = 18.dp,
            defaultHeight = 18.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).path(
            fill = SolidColor(Color.White),
            fillAlpha = 1f,
            stroke = SolidColor(Color.White),
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(5f, 3f)
            lineTo(19f, 12f)
            lineTo(5f, 21f)
            lineTo(5f, 3f)
            close()
        }.build()
    }

    val PauseIcon = remember {
        ImageVector.Builder(
            name = "Pause",
            defaultWidth = 18.dp,
            defaultHeight = 18.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).path(
            fill = SolidColor(Color.White),
            fillAlpha = 1f,
            stroke = SolidColor(Color.White),
            strokeAlpha = 1f,
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(6f, 4f)
            lineTo(10f, 4f)
            lineTo(10f, 20f)
            lineTo(6f, 20f)
            close()
            moveTo(14f, 4f)
            lineTo(18f, 4f)
            lineTo(18f, 20f)
            lineTo(14f, 20f)
            close()
        }.build()
    }

    Row(
        modifier = Modifier
            .widthIn(min = 260.dp, max = 300.dp)
            .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Play/Pause Button
        Box(
            modifier = Modifier
                .size(44.dp) // sm:w-11 sm:h-11
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f), CircleShape)
                .border(1.dp, Color.White.copy(alpha = 0.3f), CircleShape)
                .clickable { togglePlay() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isPlaying) PauseIcon else PlayIcon,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier.padding(start = if (!isPlaying) 2.dp else 0.dp) // .ml-1 equivalent for play
            )
        }

        // Waveform
        var waveformWidth by remember { mutableStateOf(0f) }
        Row(
            modifier = Modifier
                .weight(1f)
                .height(32.dp)
                .onSizeChanged { waveformWidth = it.width.toFloat() }
                .pointerInput(isPrepared, duration) {
                    detectTapGestures { offset ->
                        if (isPrepared && duration > 0 && waveformWidth > 0) {
                            val percentage = (offset.x / waveformWidth).coerceIn(0f, 1f)
                            val newTime = (percentage * duration).toInt()
                            mediaPlayer.seekTo(newTime * 1000)
                            currentTime = newTime
                        }
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val infiniteTransition = rememberInfiniteTransition(label = "waveform_anim")

            // Hoist the clock to avoid creating 40 instances
            val timeMillis by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1000000f, // Large number, acting like a clock
                animationSpec = infiniteRepeatable(
                    animation = tween(1000000, easing = LinearEasing)
                ),
                label = "clock"
            )

            bars.forEachIndexed { i, height ->
                val isPlayed = (i.toFloat() / bars.size.toFloat()) * 100f <= progressPercentage

                // Animate height if playing
                val scaleY = if (isPlaying) {
                    val delayMillis = (i * 50)
                    val period = 1200f
                    val phase = delayMillis.toFloat() / period * (2 * Math.PI.toFloat())
                    val normalizedTime = (timeMillis % period) / period * (2 * Math.PI.toFloat())
                    // Waveform play is 1 to 1.5
                    val wave = (sin(normalizedTime - phase) + 1f) / 2f // 0 to 1
                    1f + (wave * 0.5f)
                } else {
                    1f
                }

                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .fillMaxHeight(fraction = (height / 100.0).toFloat())
                        .graphicsLayer {
                            this.scaleY = scaleY
                        }
                        .background(
                            if (isPlayed) Color.White else Color.White.copy(alpha = 0.2f),
                            CircleShape
                        )
                ) {
                    if (isPlayed) {
                        // Adding a little white glow shadow can be complex in pure Compose for small boxes,
                        // but a simple overlay/border can mimic if needed.
                        // The background color handles the base look.
                    }
                }
            }
        }

        // Timer
        Text(
            text = formatTime(if (currentTime > 0) currentTime else duration),
            fontFamily = JetBrainsMono,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFCBD5E1), // slate-300
            fontSize = 11.sp,
            letterSpacing = 0.5.sp, // tracking-wider
            textAlign = TextAlign.End,
            modifier = Modifier.width(40.dp) // w-10
        )
    }
}
