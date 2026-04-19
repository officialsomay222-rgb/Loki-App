package com.lokixprime.ui.modifiers

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lokixprime.ui.theme.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.layout.offset
import kotlin.math.sin

fun Modifier.glassmorphism(
    shape: Shape = RoundedCornerShape(16.dp),
    backgroundColor: Color = GlassBackgroundDark,
    borderColor: Color = GlassBorderDark,
    borderWidth: Dp = 1.dp,
    color: Color? = null
): Modifier = this
    .clip(shape)
    .background(color ?: backgroundColor)
    .border(borderWidth, borderColor, shape)
    // Note: True blur backdrop-filter is API 31+ in Compose, using semi-transparent solid as reliable fallback.

fun Modifier.rgbAura(): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "rgbAura")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rgbRotation"
    )

    this.drawWithCache {
        val sweepGradient = Brush.sweepGradient(
            colors = listOf(
                RgbRed, RgbOrange, RgbYellow, RgbGreen, RgbCyan, RgbPurple, RgbMagenta, RgbRed
            ),
            center = Offset(size.width / 2, size.height / 2)
        )
        onDrawBehind {
            withTransform({
                rotate(degrees = rotation, pivot = Offset(size.width / 2, size.height / 2))
            }) {
                drawRect(brush = sweepGradient)
            }
        }
    }
}

fun Modifier.animatedFloatSoft(): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "floatSoft")
    val translateY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatTranslation"
    )

    this.offset(y = translateY.dp)
}

fun Modifier.glowPulse(color: Color = LokiCyan): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "glowPulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    this.drawWithCache {
        onDrawBehind {
            drawCircle(
                color = color.copy(alpha = alpha),
                radius = size.maxDimension / 2 + 40f
            )
        }
    }
}

fun Modifier.sweepingShine(): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "sweepingShine")
    val translate by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shineTranslation"
    )

    this.drawWithCache {
        onDrawWithContent {
            drawContent()
            clipRect {
                val startX = size.width * translate
                val endX = startX + size.width
                val shineBrush = Brush.linearGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.White.copy(alpha = 0.8f),
                        Color.White,
                        Color.White.copy(alpha = 0.8f),
                        Color.Transparent
                    ),
                    start = Offset(startX, 0f),
                    end = Offset(endX, size.height)
                )
                drawRect(
                    brush = shineBrush,
                    blendMode = BlendMode.Overlay
                )
            }
        }
    }
}
