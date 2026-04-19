package com.lokixprime.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun InfinityLogo(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinity")

    val rotationX by infiniteTransition.animateFloat(
        initialValue = 5f,
        targetValue = -5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotationX"
    )

    val rotationY by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotationY"
    )

    val translateY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "translateY"
    )

    val dashOffset by infiniteTransition.animateFloat(
        initialValue = 500f,
        targetValue = -500f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "dashOffset"
    )

    val rainbowColors = listOf(
        Color(0xFFFF0000),
        Color(0xFFFF7F00),
        Color(0xFFFFFF00),
        Color(0xFF00FF00),
        Color(0xFF00F0FF),
        Color(0xFFBD00FF),
        Color(0xFFFF00FF),
        Color(0xFFFF0000)
    )

    val colorIndex by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = rainbowColors.size.toFloat() - 1,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "colorIndex"
    )

    Canvas(modifier = modifier.graphicsLayer {
        this.translationY = translateY
        this.rotationX = rotationX
        this.rotationY = rotationY
        this.cameraDistance = 1000f
    }) {
        val path = Path().apply {
            moveTo(50f, 50f)
            cubicTo(30f, 30f, 20f, 50f, 30f, 70f)
            cubicTo(40f, 90f, 60f, 90f, 80f, 70f)
            cubicTo(100f, 50f, 120f, 50f, 140f, 70f)
            cubicTo(160f, 90f, 180f, 90f, 170f, 70f)
            cubicTo(160f, 50f, 140f, 30f, 120f, 50f)
            cubicTo(100f, 70f, 80f, 70f, 60f, 50f)
            cubicTo(40f, 30f, 30f, 30f, 50f, 50f)
        }

        // Scale path to fit canvas
        val scaleX = size.width / 200f
        val scaleY = size.height / 100f
        val matrix = Matrix()
        matrix.scale(scaleX, scaleY)
        path.transform(matrix)

        val brush = Brush.linearGradient(
            colors = rainbowColors,
            start = Offset.Zero,
            end = Offset(size.width, 0f)
        )

        // Draw glow
        drawPath(
            path = path,
            brush = brush,
            style = Stroke(width = 10f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(500f, 500f), dashOffset)),
            alpha = 0.3f
        )

        // Draw main path
        drawPath(
            path = path,
            brush = brush,
            style = Stroke(width = 6f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(500f, 500f), dashOffset))
        )
    }
}

@Composable
fun HeaderInfinityLogo(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "header_infinity")

    val dashOffset by infiniteTransition.animateFloat(
        initialValue = 100f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "dashOffset"
    )

    val rainbowColors = listOf(
        Color(0xFFFF0000),
        Color(0xFFFF7F00),
        Color(0xFFFFFF00),
        Color(0xFF00FF00),
        Color(0xFF00F0FF),
        Color(0xFFBD00FF),
        Color(0xFFFF00FF),
        Color(0xFFFF0000)
    )

    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(25f, 15f)
            cubicTo(5f, 15f, 5f, 35f, 25f, 35f)
            cubicTo(45f, 35f, 55f, 15f, 75f, 15f)
            cubicTo(95f, 15f, 95f, 35f, 75f, 35f)
            cubicTo(55f, 35f, 45f, 15f, 25f, 15f)
        }

        val scaleX = size.width / 100f
        val scaleY = size.height / 50f
        val matrix = Matrix()
        matrix.scale(scaleX, scaleY)
        path.transform(matrix)

        drawPath(
            path = path,
            brush = Brush.linearGradient(
                colors = rainbowColors,
                start = Offset.Zero,
                end = Offset(size.width, 0f)
            ),
            style = Stroke(
                width = 7f,
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(60f, 40f), dashOffset)
            )
        )
    }
}
