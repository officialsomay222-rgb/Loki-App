package com.lokixprime.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.coroutines.isActive
import kotlin.math.*
import kotlin.random.Random

class Particle(
    var x: Float,
    var y: Float,
    val color: Color,
    var radius: Float
) {
    var angle = Random.nextFloat() * 2 * PI.toFloat()
    var speed = Random.nextFloat() * 12f + 4f
    var vx = cos(angle) * speed
    var vy = sin(angle) * speed
    val maxLife = Random.nextFloat() * 80f + 50f
    var life = maxLife

    fun update(progress: Float, frameCount: Int) {
        val swirlForce = 0.08f
        val tangentX = -vy * swirlForce
        val tangentY = vx * swirlForce

        val sineWobble = sin(frameCount * 0.1f + life) * 0.5f

        vx += tangentX + cos(angle + PI.toFloat() / 2f) * sineWobble
        vy += tangentY + sin(angle + PI.toFloat() / 2f) * sineWobble

        vx *= 0.95f
        vy *= 0.95f

        x += vx * (1 + progress * 2)
        y += vy * (1 + progress * 2)

        life -= 1f
        radius *= 0.97f
    }

    fun draw(drawScope: DrawScope, globalAlpha: Float) {
        if (life <= 0 || radius <= 0.1f) return

        val lifeRatio = life / maxLife
        val alpha = lifeRatio.pow(1.5f) * globalAlpha

        drawScope.drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(color.copy(alpha = alpha * 0.9f), color.copy(alpha = alpha * 0.5f), Color.Transparent),
                center = Offset(x, y),
                radius = radius
            ),
            radius = radius,
            center = Offset(x, y),
            blendMode = BlendMode.Plus
        )
    }
}

class ShockwaveRing(
    val x: Float,
    val y: Float,
    val maxRadius: Float
) {
    var radius = 0f
    val maxLife = 60f
    var life = maxLife
    var thickness = 15f

    fun update() {
        life -= 1f
        val progress = 1f - (life / maxLife)
        val easeOutCubic = 1f - (1f - progress).pow(3f)
        radius = easeOutCubic * maxRadius
        thickness = 15f * (life / maxLife)
    }

    fun draw(drawScope: DrawScope, globalAlpha: Float) {
        if (life <= 0) return

        val alpha = (life / maxLife) * globalAlpha * 0.6f

        drawScope.drawCircle(
            color = Color(0xFF00F2FF).copy(alpha = alpha),
            radius = radius,
            center = Offset(x, y),
            style = Stroke(width = thickness),
            blendMode = BlendMode.Plus
        )

        drawScope.drawCircle(
            color = Color.White.copy(alpha = alpha * 0.5f),
            radius = radius * 0.95f,
            center = Offset(x, y),
            style = Stroke(width = thickness * 0.5f),
            blendMode = BlendMode.Plus
        )
    }
}

@Composable
fun PremiumLiquidShockwave(
    onAnimationEnd: () -> Unit = {}
) {
    val particles = remember { mutableStateListOf<Particle>() }
    val rings = remember { mutableStateListOf<ShockwaveRing>() }
    var frameCount by remember { mutableStateOf(0) }
    val progress = remember { Animatable(0f) }

    val colors = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Blue
    )

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 5000, easing = LinearEasing)
        )
        onAnimationEnd()
    }

    LaunchedEffect(Unit) {
        while (isActive) {
            withFrameNanos {
                frameCount++
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2f
        val centerY = size.height * 0.35f
        val currentProgress = progress.value
        val globalAlpha = if (currentProgress > 0.8f) {
            1f - ((currentProgress - 0.8f) / 0.2f)
        } else {
            1f
        }

        // 1. Massive continuous liquid aura base
        val maxRadiusSize = max(size.width, size.height) * 1.2f
        val easeOutQuart = 1f - (1f - currentProgress).pow(4f)
        val currentRadius = 50f + (easeOutQuart * maxRadiusSize)

        if (currentRadius > 0 && globalAlpha > 0) {
            val timeOffset = sin(frameCount * 0.05f)

            drawCircle(
                brush = Brush.radialGradient(
                    0.0f to Color.Red.copy(alpha = globalAlpha * (0.4f + timeOffset * 0.1f)),
                    0.2f to Color.Yellow.copy(alpha = globalAlpha * 0.3f),
                    0.5f to Color.Green.copy(alpha = globalAlpha * 0.2f),
                    0.8f to Color.Blue.copy(alpha = globalAlpha * 0.05f),
                    1.0f to Color.Transparent,
                    center = Offset(centerX, centerY),
                    radius = currentRadius
                ),
                radius = currentRadius,
                center = Offset(centerX, centerY),
                blendMode = BlendMode.Plus
            )
        }

        // 2. Add Shockwave Rings
        if (currentProgress < 0.8f && frameCount % 45 == 0) {
            rings.add(ShockwaveRing(centerX, centerY, min(size.width, size.height) * 0.8f))
        }

        rings.forEach { r ->
            r.update()
            r.draw(this, globalAlpha)
        }
        rings.removeAll { it.life <= 0 }

        // 3. Spawning particles
        if (currentProgress < 0.85f) {
            val spawnCount = Random.nextInt(5) + 8
            repeat(spawnCount) {
                val color = colors.random()
                val angle = Random.nextFloat() * 2 * PI.toFloat()
                val dist = Random.nextFloat() * 40f
                particles.add(
                    Particle(
                        centerX + cos(angle) * dist,
                        centerY + sin(angle) * dist,
                        color,
                        Random.nextFloat() * 18f + 5f
                    )
                )
            }
        }

        particles.forEach { p ->
            p.update(currentProgress, frameCount)
            p.draw(this, globalAlpha)
        }
        particles.removeAll { it.life <= 0 || it.radius <= 0.1f }

        // 4. Intense energy core
        val coreSize = 70f + sin(frameCount * 0.15f) * 15f
        val coreGlowAlpha = globalAlpha * (0.8f + sin(frameCount * 0.2f) * 0.2f)

        drawCircle(
            brush = Brush.radialGradient(
                0.0f to Color.White.copy(alpha = coreGlowAlpha),
                0.2f to Color(0xFF00F2FF).copy(alpha = coreGlowAlpha * 0.9f),
                0.5f to Color.Yellow.copy(alpha = coreGlowAlpha * 0.7f),
                1.0f to Color.Transparent,
                center = Offset(centerX, centerY),
                radius = coreSize
            ),
            radius = coreSize,
            center = Offset(centerX, centerY),
            blendMode = BlendMode.Screen
        )
    }
}
