package com.lokixprime.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.em
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin

val WifiOffIcon: ImageVector
    get() = ImageVector.Builder(
        name = "WifiOff",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            stroke = androidx.compose.ui.graphics.SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(12f, 20f)
            lineTo(12.01f, 20f)
        }
        path(
            stroke = androidx.compose.ui.graphics.SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(8.5f, 16.429f)
            arcToRelative(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 7f, dy1 = 0f)
        }
        path(
            stroke = androidx.compose.ui.graphics.SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(5f, 12.859f)
            arcToRelative(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 5.17f, dy1 = -2.69f)
        }
        path(
            stroke = androidx.compose.ui.graphics.SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(19f, 12.859f)
            arcToRelative(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -2.007f, dy1 = -1.523f)
        }
        path(
            stroke = androidx.compose.ui.graphics.SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(2f, 8.82f)
            arcToRelative(15f, 15f, 0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 4.177f, dy1 = -2.643f)
        }
        path(
            stroke = androidx.compose.ui.graphics.SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(22f, 8.82f)
            arcToRelative(15f, 15f, 0f, isMoreThanHalf = false, isPositiveArc = false, dx1 = -11.288f, dy1 = -3.764f)
        }
        path(
            stroke = androidx.compose.ui.graphics.SolidColor(Color.White),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(2f, 2f)
            lineToRelative(20f, 20f)
        }
    }.build()

@Composable
fun NetworkStatusIndicator(connected: Boolean) {
    AnimatedVisibility(
        visible = !connected,
        enter = slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(durationMillis = 300)),
        exit = slideOutVertically(
            targetOffsetY = { -it },
            animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
        ) + fadeOut(animationSpec = tween(durationMillis = 300)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp) // mt-safe pt-2 equivalent
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "pulse")
        val alpha by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 0.5f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulseAlpha"
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        spotColor = Color.Red.copy(alpha = 0.2f)
                    )
                    .background(
                        color = Color.Red.copy(alpha = 0.9f),
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Red.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp), // px-4 py-1.5
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // gap-2
            ) {
                Icon(
                    imageVector = WifiOffIcon,
                    contentDescription = "No Internet Connection",
                    modifier = Modifier
                        .size(16.dp) // w-4 h-4
                        .alpha(alpha), // animate-pulse
                    tint = Color.White
                )
                Text(
                    text = "No Internet Connection",
                    color = Color.White,
                    fontSize = 14.sp, // text-sm
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.025.em // tracking-wide
                )
            }
        }
    }
}
