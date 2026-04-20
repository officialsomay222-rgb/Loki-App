package com.lokixprime.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Play: ImageVector
    get() = ImageVector.Builder(
        name = "Play",
        defaultWidth = 18.dp,
        defaultHeight = 18.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            fill = SolidColor(Color.White),
            stroke = SolidColor(Color.White),
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            // <polygon points="5 3 19 12 5 21 5 3"></polygon>
            moveTo(5f, 3f)
            lineTo(19f, 12f)
            lineTo(5f, 21f)
            lineTo(5f, 3f)
            close()
        }
    }.build()

val Pause: ImageVector
    get() = ImageVector.Builder(
        name = "Pause",
        defaultWidth = 18.dp,
        defaultHeight = 18.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            fill = SolidColor(Color.White),
            stroke = SolidColor(Color.White),
            strokeLineWidth = 1f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            // <rect x="6" y="4" width="4" height="16" rx="1"></rect>
            moveTo(7f, 4f)
            lineTo(9f, 4f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1f, 1f)
            lineTo(10f, 19f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1f, 1f)
            lineTo(7f, 20f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1f, -1f)
            lineTo(6f, 5f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1f, -1f)
            close()

            // <rect x="14" y="4" width="4" height="16" rx="1"></rect>
            moveTo(15f, 4f)
            lineTo(17f, 4f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1f, 1f)
            lineTo(18f, 19f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1f, 1f)
            lineTo(15f, 20f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, -1f, -1f)
            lineTo(14f, 5f)
            arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = true, 1f, -1f)
            close()
        }
    }.build()
