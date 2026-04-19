package com.lokixprime.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object LokiIcons {
    // Lucide Menu
    val Menu: ImageVector
        get() = ImageVector.Builder(
            name = "Menu",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(4f, 12f)
                lineTo(20f, 12f)
                moveTo(4f, 6f)
                lineTo(20f, 6f)
                moveTo(4f, 18f)
                lineTo(20f, 18f)
            }
        }.build()

    // Lucide Settings
    val Settings: ImageVector
        get() = ImageVector.Builder(
            name = "Settings",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12.22f, 2f)
                horizontalLineToRelative(-.44f)
                arcToRelative(2f, 2f, 0f, false, false, -2f, 2f)
                verticalLineToRelative(.18f)
                arcToRelative(2f, 2f, 0f, false, true, -1f, 1.73f)
                lineToRelative(-.43f, .25f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, 0f)
                lineToRelative(-.15f, -.08f)
                arcToRelative(2f, 2f, 0f, false, false, -2.73f, .73f)
                lineToRelative(-.22f, .38f)
                arcToRelative(2f, 2f, 0f, false, false, .73f, 2.73f)
                lineToRelative(.15f, .1f)
                arcToRelative(2f, 2f, 0f, false, true, 1f, 1.72f)
                verticalLineToRelative(.51f)
                arcToRelative(2f, 2f, 0f, false, true, -1f, 1.74f)
                lineToRelative(-.15f, .09f)
                arcToRelative(2f, 2f, 0f, false, false, -.73f, 2.73f)
                lineToRelative(.22f, .38f)
                arcToRelative(2f, 2f, 0f, false, false, 2.73f, .73f)
                lineToRelative(.15f, -.08f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, 0f)
                lineToRelative(.43f, .25f)
                arcToRelative(2f, 2f, 0f, false, true, 1f, 1.73f)
                verticalLineToRelative(.2f)
                arcToRelative(2f, 2f, 0f, false, false, 2f, 2f)
                horizontalLineToRelative(.44f)
                arcToRelative(2f, 2f, 0f, false, false, 2f, -2f)
                verticalLineToRelative(-.18f)
                arcToRelative(2f, 2f, 0f, false, true, 1f, -1.73f)
                lineToRelative(.43f, -.25f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, 0f)
                lineToRelative(.15f, .08f)
                arcToRelative(2f, 2f, 0f, false, false, 2.73f, -.73f)
                lineToRelative(.22f, -.39f)
                arcToRelative(2f, 2f, 0f, false, false, -.73f, -2.73f)
                lineToRelative(-.15f, -.08f)
                arcToRelative(2f, 2f, 0f, false, true, -1f, -1.74f)
                verticalLineTo(-.5f)
                arcToRelative(2f, 2f, 0f, false, true, 1f, -1.74f)
                lineToRelative(.15f, -.09f)
                arcToRelative(2f, 2f, 0f, false, false, .73f, -2.73f)
                lineToRelative(-.22f, -.38f)
                arcToRelative(2f, 2f, 0f, false, false, -2.73f, -.73f)
                lineToRelative(-.15f, .08f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, 0f)
                lineToRelative(-.43f, -.25f)
                arcToRelative(2f, 2f, 0f, false, true, -1f, -1.73f)
                verticalLineToRelative(-.2f)
                arcToRelative(2f, 2f, 0f, false, false, -2f, -2f)
                close()
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 12f)
                moveToRelative(-3f, 0f)
                arcToRelative(3f, 3f, 0f, true, true, 6f, 0f)
                arcToRelative(3f, 3f, 0f, true, true, -6f, 0f)
            }
        }.build()

    // Lucide Send
    val Send: ImageVector
        get() = ImageVector.Builder(
            name = "Send",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(22f, 2f)
                lineTo(11f, 13f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(22f, 2f)
                lineToRelative(-7f, 20f)
                lineToRelative(-4f, -9f)
                lineToRelative(-9f, -4f)
                close()
            }
        }.build()

    // Lucide Mic
    val Mic: ImageVector
        get() = ImageVector.Builder(
            name = "Mic",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 2f)
                arcToRelative(3f, 3f, 0f, false, false, -3f, 3f)
                verticalLineToRelative(7f)
                arcToRelative(3f, 3f, 0f, false, false, 6f, 0f)
                verticalLineTo(5f)
                arcToRelative(3f, 3f, 0f, false, false, -3f, -3f)
                close()
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(19f, 10f)
                verticalLineToRelative(2f)
                arcToRelative(7f, 7f, 0f, false, true, -14f, 0f)
                verticalLineToRelative(-2f)
                moveTo(12f, 19f)
                verticalLineToRelative(4f)
                moveTo(8f, 23f)
                horizontalLineToRelative(8f)
            }
        }.build()

    // Lucide ArrowDown
    val ArrowDown: ImageVector
        get() = ImageVector.Builder(
            name = "ArrowDown",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 5f)
                verticalLineToRelative(14f)
                moveTo(19f, 12f)
                lineToRelative(-7f, 7f)
                lineToRelative(-7f, -7f)
            }
        }.build()

    // Lucide Close/X
    val X: ImageVector
        get() = ImageVector.Builder(
            name = "X",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(18f, 6f)
                lineTo(6f, 18f)
                moveTo(6f, 6f)
                lineToRelative(12f, 12f)
            }
        }.build()

    // Lucide ChevronRight
    val ChevronRight: ImageVector
        get() = ImageVector.Builder(
            name = "ChevronRight",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9f, 18f)
                lineToRelative(6f, -6f)
                lineToRelative(-6f, -6f)
            }
        }.build()

    // Lucide Zap (for Try Loki Prime X Pro banner)
    val Zap: ImageVector
        get() = ImageVector.Builder(
            name = "Zap",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(13f, 2f)
                lineTo(3f, 14f)
                horizontalLineToRelative(9f)
                lineToRelative(-1f, 8f)
                lineToRelative(10f, -12f)
                horizontalLineToRelative(-9f)
                lineToRelative(1f, -8f)
                close()
            }
        }.build()

    // Lucide Monitor
    val Monitor: ImageVector
        get() = ImageVector.Builder(
            name = "Monitor",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(2f, 3f)
                horizontalLineToRelative(20f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
                verticalLineToRelative(10f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
                horizontalLineTo(2f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, -2f)
                verticalLineTo(5f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
                close()
                moveTo(8f, 21f)
                horizontalLineToRelative(8f)
                moveTo(12f, 17f)
                verticalLineToRelative(4f)
            }
        }.build()

    // Lucide Volume2
    val Volume2: ImageVector
        get() = ImageVector.Builder(
            name = "Volume2",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(11f, 5f)
                lineTo(6f, 9f)
                horizontalLineTo(2f)
                verticalLineToRelative(6f)
                horizontalLineToRelative(4f)
                lineToRelative(5f, 4f)
                verticalLineTo(5f)
                close()
                moveTo(15.54f, 8.46f)
                arcToRelative(5f, 5f, 0f, false, true, 0f, 7.07f)
                moveTo(19.07f, 4.93f)
                arcToRelative(10f, 10f, 0f, false, true, 0f, 14.14f)
            }
        }.build()

    // Lucide Plus
    val Plus: ImageVector
        get() = ImageVector.Builder(
            name = "Plus",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 5f)
                lineTo(12f, 19f)
                moveTo(5f, 12f)
                lineTo(19f, 12f)
            }
        }.build()

    // Lucide ChevronDown
    val ChevronDown: ImageVector
        get() = ImageVector.Builder(
            name = "ChevronDown",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(6f, 9f)
                lineToRelative(6f, 6f)
                lineToRelative(6f, -6f)
            }
        }.build()
}
