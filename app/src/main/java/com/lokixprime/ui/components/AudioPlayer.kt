package com.lokixprime.ui.components

import android.media.MediaPlayer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

// Lucide Pause Icon
val PauseIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Pause",
        defaultWidth = 18.dp,
        defaultHeight = 18.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
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
        }
    }.build()

// Lucide Play Icon
val PlayIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Play",
        defaultWidth = 18.dp,
        defaultHeight = 18.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(5f, 3f)
            lineTo(19f, 12f)
            lineTo(5f, 21f)
            close()
        }
    }.build()

@Composable
fun AudioPlayer(
    url: String,
    autoPlay: Boolean = false,
    onPlay: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }
    var isPrepared by remember { mutableStateOf(false) }
    var duration by remember { mutableStateOf(0) }
    var currentTime by remember { mutableStateOf(0) }

    val mediaPlayer = remember(url) {
        MediaPlayer().apply {
            try {
                setDataSource(url)
                prepareAsync()
            } catch (e: Exception) {
                // Handle invalid URL or other data source errors
            }
        }
    }

    DisposableEffect(mediaPlayer) {
        mediaPlayer.setOnPreparedListener { mp ->
            isPrepared = true
            duration = mp.duration
            if (autoPlay) {
                mp.start()
                isPlaying = true
                onPlay?.invoke()
            }
        }
        mediaPlayer.setOnCompletionListener {
            isPlaying = false
            currentTime = 0
            // Reset player to beginning
            it.seekTo(0)
        }

        onDispose {
            if (mediaPlayer.isPlaying) mediaPlayer.stop()
            mediaPlayer.release()
            isPrepared = false
            isPlaying = false
        }
    }

    LaunchedEffect(isPlaying, isPrepared) {
        while (isPlaying && isPrepared) {
            try {
                currentTime = mediaPlayer.currentPosition
            } catch (e: Exception) {
                // Handle illegal state if media player is released
            }
            delay(100) // Update every 100ms
        }
    }

    // Use a fixed seed for the waveform so it doesn't change on re-renders
    val bars = remember(url) {
        Array(40) { i ->
            val valCalc = sin(i * 0.5) * 30 + cos(i * 0.2) * 20 + 50
            max(20.0, min(100.0, valCalc)).toFloat()
        }
    }

    fun togglePlay() {
        if (!isPrepared) return
        if (isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
            onPlay?.invoke()
        }
        isPlaying = !isPlaying
    }

    fun formatTime(timeMillis: Int): String {
        val totalSeconds = timeMillis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    val progressPercentage = if (duration > 0) (currentTime.toFloat() / duration.toFloat()) * 100f else 0f

    Row(
        modifier = modifier
            .width(260.dp)
            .background(Color(0x66000000), RoundedCornerShape(8.dp)) // bg-black/40 (approx 40% alpha is 0x66)
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Play/Pause Button
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0x33FFFFFF)) // bg-white/20
                .alpha(if (isPrepared) 1f else 0.5f)
                .clickable(enabled = isPrepared) { togglePlay() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isPlaying) PauseIcon else PlayIcon,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier.padding(if (!isPlaying) PaddingValues(start = 2.dp) else PaddingValues(0.dp))
            )
        }

        // Waveform
        Row(
            modifier = Modifier
                .weight(1f)
                .height(32.dp)
                .pointerInput(duration, isPrepared) {
                    detectTapGestures { offset ->
                        if (duration > 0 && isPrepared) {
                            val percentage = offset.x / size.width
                            val newTime = (percentage * duration).toInt()
                            mediaPlayer.seekTo(newTime)
                            currentTime = newTime
                        }
                    }
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bars.forEachIndexed { i, height ->
                val isPlayed = (i.toFloat() / bars.size) * 100f <= progressPercentage

                // Animate height slightly when playing
                val animatedHeight by animateFloatAsState(
                    targetValue = if (isPlaying && !isPlayed) height * (0.8f + (Math.random() * 0.4f).toFloat()) else height,
                    animationSpec = tween(durationMillis = 150),
                    label = "heightAnimation"
                )

                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .fillMaxHeight(fraction = animatedHeight / 100f)
                        .clip(CircleShape)
                        .background(
                            if (isPlayed) Color.White
                            else Color(0x33FFFFFF) // bg-white/20
                        )
                )
            }
        }

        // Time indicator
        Text(
            text = formatTime(if (currentTime > 0) currentTime else duration),
            color = Color(0xFFCBD5E1), // slate-300
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.sp,
            textAlign = TextAlign.Right,
            modifier = Modifier.width(40.dp)
        )
    }
}
