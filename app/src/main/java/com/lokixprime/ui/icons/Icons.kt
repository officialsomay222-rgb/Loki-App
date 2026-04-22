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

    // Lucide Sparkles
    val Sparkles: ImageVector
        get() = ImageVector.Builder(
            name = "Sparkles",
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
                moveTo(9.937f, 15.5f)
                arcTo(2f, 2f, 0f, false, false, 8.5f, 14.063f)
                lineToRelative(-6.135f, -1.582f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0f, -0.962f)
                lineTo(8.5f, 9.936f)
                arcTo(2f, 2f, 0f, false, false, 9.937f, 8.5f)
                lineToRelative(1.582f, -6.135f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.963f, 0f)
                lineTo(14.063f, 8.5f)
                arcTo(2f, 2f, 0f, false, false, 15.5f, 9.937f)
                lineToRelative(6.135f, 1.581f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0f, 0.964f)
                lineTo(15.5f, 14.063f)
                arcToRelative(2f, 2f, 0f, false, false, -1.437f, 1.437f)
                lineToRelative(-1.582f, 6.135f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -0.963f, 0f)
                close()
                moveTo(20f, 3f)
                verticalLineToRelative(4f)
                moveTo(22f, 5f)
                horizontalLineToRelative(-4f)
                moveTo(4f, 17f)
                verticalLineToRelative(2f)
                moveTo(5f, 18f)
                horizontalLineTo(3f)
            }
        }.build()

    // Lucide ArrowRight
    val ArrowRight: ImageVector
        get() = ImageVector.Builder(
            name = "ArrowRight",
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
                moveTo(5f, 12f)
                horizontalLineToRelative(14f)
                moveTo(12f, 5f)
                lineToRelative(7f, 7f)
                lineToRelative(-7f, 7f)
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

    val SlidersHorizontal: ImageVector
        get() = ImageVector.Builder(
            name = "SlidersHorizontal",
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
                moveTo(10f, 5f)
                lineTo(3f, 5f)
                moveTo(12f, 19f)
                lineTo(3f, 19f)
                moveTo(14f, 3f)
                lineTo(14f, 7f)
                moveTo(16f, 17f)
                lineTo(16f, 21f)
                moveTo(21f, 12f)
                lineTo(12f, 12f)
                moveTo(21f, 19f)
                lineTo(16f, 19f)
                moveTo(21f, 5f)
                lineTo(14f, 5f)
                moveTo(8f, 10f)
                lineTo(8f, 14f)
                moveTo(8f, 12f)
                lineTo(3f, 12f)
            }
        }.build()

    val Globe: ImageVector
        get() = ImageVector.Builder(
            name = "Globe",
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
                moveTo(12f, 12f)
                moveToRelative(-10f, 0f)
                arcToRelative(10f, 10f, 0f, true, true, 20f, 0f)
                arcToRelative(10f, 10f, 0f, true, true, -20f, 0f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(12f, 2f)
                arcToRelative(14.5f, 14.5f, 0f, false, false, 0f, 20f)
                arcToRelative(14.5f, 14.5f, 0f, false, false, 0f, -20f)
                moveTo(2f, 12f)
                lineTo(22f, 12f)
            }
        }.build()

    val ImageIcon: ImageVector
        get() = ImageVector.Builder(
            name = "ImageIcon",
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
                moveTo(5f, 3f)
                lineTo(19f, 3f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
                lineTo(21f, 19f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
                lineTo(5f, 21f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, -2f)
                lineTo(3f, 5f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
                close()
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9f, 9f)
                moveToRelative(-2f, 0f)
                arcToRelative(2f, 2f, 0f, true, true, 4f, 0f)
                arcToRelative(2f, 2f, 0f, true, true, -4f, 0f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21f, 15f)
                lineToRelative(-3.086f, -3.086f)
                arcToRelative(2f, 2f, 0f, false, false, -2.828f, 0f)
                lineTo(6f, 21f)
            }
        }.build()

    val Brain: ImageVector
        get() = ImageVector.Builder(
            name = "Brain",
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
                moveTo(12f, 18f)
                lineTo(12f, 5f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(15f, 13f)
                arcToRelative(4.17f, 4.17f, 0f, false, true, -3f, -4f)
                arcToRelative(4.17f, 4.17f, 0f, false, true, -3f, 4f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(17.598f, 6.5f)
                arcTo(3f, 3f, 0f, true, false, 12f, 5f)
                arcToRelative(3f, 3f, 0f, true, false, -5.598f, 1.5f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(17.997f, 5.125f)
                arcToRelative(4f, 4f, 0f, false, true, 2.526f, 5.77f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(18f, 18f)
                arcToRelative(4f, 4f, 0f, false, false, 2f, -7.464f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(19.967f, 17.483f)
                arcTo(4f, 4f, 0f, true, true, 12f, 18f)
                arcToRelative(4f, 4f, 0f, true, true, -7.967f, -.517f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(6f, 18f)
                arcToRelative(4f, 4f, 0f, false, true, -2f, -7.464f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(6.003f, 5.125f)
                arcToRelative(4f, 4f, 0f, false, false, -2.526f, 5.77f)
            }
        }.build()

    val Smile: ImageVector
        get() = ImageVector.Builder(
            name = "Smile",
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
                moveTo(12f, 12f)
                moveToRelative(-10f, 0f)
                arcToRelative(10f, 10f, 0f, true, true, 20f, 0f)
                arcToRelative(10f, 10f, 0f, true, true, -20f, 0f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(8f, 14f)
                curveToRelative(1.5f, 2f, 4f, 2f, 4f, 2f)
                curveToRelative(0f, 0f, 2.5f, 0f, 4f, -2f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9f, 9f)
                lineTo(9.01f, 9f)
                moveTo(15f, 9f)
                lineTo(15.01f, 9f)
            }
        }.build()



    // Lucide PanelLeftOpen
    val PanelLeftOpen: ImageVector
        get() = ImageVector.Builder(
            name = "PanelLeftOpen",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(18f, 3f)
                lineTo(6f, 3f)
                arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3f, 6f)
                lineTo(3f, 18f)
                arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 6f, 21f)
                lineTo(18f, 21f)
                arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 21f, 18f)
                lineTo(21f, 6f)
                arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = false, 18f, 3f)
                close()
                moveTo(9f, 3f)
                lineTo(9f, 21f)
                moveTo(15f, 15f)
                lineTo(18f, 12f)
                lineTo(15f, 9f)
            }
        }.build()

    // Lucide Rocket
    val Rocket: ImageVector
        get() = ImageVector.Builder(
            name = "Rocket",
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
                // M12 15v5s3.03-.55 4-2c1.08-1.62 0-5 0-5
                moveTo(12f, 15f)
                lineTo(12f, 20f)
                curveToRelative(0f, 0f, 3.03f, -0.55f, 4f, -2f)
                curveToRelative(1.08f, -1.62f, 0f, -5f, 0f, -5f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // M4.5 16.5c-1.5 1.26-2 5-2 5s3.74-.5 5-2c.71-.84.7-2.13-.09-2.91a2.18 2.18 0 0 0-2.91-.09
                moveTo(4.5f, 16.5f)
                curveToRelative(-1.5f, 1.26f, -2f, 5f, -2f, 5f)
                reflectiveCurveToRelative(3.74f, -0.5f, 5f, -2f)
                curveToRelative(0.71f, -0.84f, 0.7f, -2.13f, -0.09f, -2.91f)
                arcToRelative(2.18f, 2.18f, 0f, false, false, -2.91f, -0.09f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // M9 12a22 22 0 0 1 2-3.95A12.88 12.88 0 0 1 22 2c0 2.72-.78 7.5-6 11a22.4 22.4 0 0 1-4 2z
                moveTo(9f, 12f)
                arcToRelative(22f, 22f, 0f, false, true, 2f, -3.95f)
                arcTo(12.88f, 12.88f, 0f, false, true, 22f, 2f)
                curveToRelative(0f, 2.72f, -0.78f, 7.5f, -6f, 11f)
                arcToRelative(22.4f, 22.4f, 0f, false, true, -4f, 2f)
                close()
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // M9 12H4s.55-3.03 2-4c1.62-1.08 5 .05 5 .05
                moveTo(9f, 12f)
                lineTo(4f, 12f)
                curveToRelative(0f, 0f, 0.55f, -3.03f, 2f, -4f)
                curveToRelative(1.62f, -1.08f, 5f, 0.05f, 5f, 0.05f)
            }
        }.build()

    // Lucide AppWindow
    val AppWindow: ImageVector
        get() = ImageVector.Builder(
            name = "AppWindow",
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
                // rect x="2" y="4" width="20" height="16" rx="2"
                moveTo(4f, 4f)
                lineTo(20f, 4f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
                lineTo(22f, 18f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
                lineTo(4f, 20f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, -2f)
                lineTo(2f, 6f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
                close()
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // M10 4v4
                moveTo(10f, 4f)
                lineTo(10f, 8f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // M2 8h20
                moveTo(2f, 8f)
                lineTo(22f, 8f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // M6 4v4
                moveTo(6f, 4f)
                lineTo(6f, 8f)
            }
        }.build()

    // Lucide ExternalLink
    val ExternalLink: ImageVector
        get() = ImageVector.Builder(
            name = "ExternalLink",
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
                // M15 3h6v6
                moveTo(15f, 3f)
                lineTo(21f, 3f)
                lineTo(21f, 9f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // M10 14 21 3
                moveTo(10f, 14f)
                lineTo(21f, 3f)
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // M18 13v6a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h6
                moveTo(18f, 13f)
                lineTo(18f, 19f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
                lineTo(5f, 21f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, -2f)
                lineTo(3f, 8f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
                lineTo(11f, 6f)
            }
        }.build()

    val Trash2: ImageVector
        get() = ImageVector.Builder(
            name = "Trash2",
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
                // M3 6h18
                moveTo(3f, 6f)
                lineTo(21f, 6f)
                // M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6
                moveTo(19f, 6f)
                lineTo(19f, 20f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 17f, 22f)
                lineTo(7f, 22f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 5f, 20f)
                lineTo(5f, 6f)
                // M8 6V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2
                moveTo(8f, 6f)
                lineTo(8f, 4f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 2f)
                lineTo(14f, 2f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 4f)
                lineTo(16f, 6f)
                // M10 11v6
                moveTo(10f, 11f)
                lineTo(10f, 17f)
                // M14 11v6
                moveTo(14f, 11f)
                lineTo(14f, 17f)
            }
        }.build()

    // Lucide WifiOff
    val WifiOff: ImageVector
        get() = ImageVector.Builder(
            name = "WifiOff",
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
                // <path d="M2 2l20 20" />
                moveTo(2f, 2f)
                lineTo(22f, 22f)
                // <path d="M8.53 8.53a10 10 0 0 1 12.02 2.37" />
                moveTo(8.53f, 8.53f)
                arcToRelative(10f, 10f, 0f, false, true, 12.02f, 2.37f)
                // <path d="M4.93 4.93A14 14 0 0 1 12 3a14.07 14.07 0 0 1 3.55.45" />
                moveTo(4.93f, 4.93f)
                arcToRelative(14f, 14f, 0f, false, true, 7.07f, -1.93f)
                arcToRelative(14.07f, 14.07f, 0f, false, true, 3.55f, 0.45f)
                // <path d="M4.93 19.07A10 10 0 0 1 2 15" />
                moveTo(4.93f, 19.07f)
                arcToRelative(10f, 10f, 0f, false, true, -2.93f, -4.07f)
                // <path d="M15.54 15.54a5 5 0 0 0-7.08 0" />
                moveTo(15.54f, 15.54f)
                arcToRelative(5f, 5f, 0f, false, false, -7.08f, 0f)
                // <path d="M12 20h.01" />
                moveTo(12f, 20f)
                horizontalLineToRelative(0.01f)
            }
        }.build()


    // Lucide Pin
    val Pin: ImageVector
        get() = ImageVector.Builder(
            name = "Pin",
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
                moveTo(15f, 5f)
                lineTo(9f, 11f)
                lineTo(4f, 11f)
                lineTo(11f, 18f)
                verticalLineToRelative(5f)
                lineTo(13f, 21f)
                lineTo(13f, 18f)
                lineTo(18f, 13f)
                verticalLineToRelative(-5f)
                close()
            }
        }.build()

    // Lucide MessageSquare
    val MessageSquare: ImageVector
        get() = ImageVector.Builder(
            name = "MessageSquare",
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
                moveTo(21f, 15f)
                arcToRelative(2f, 2f, 0f, false, true, -2f, 2f)
                horizontalLineTo(7f)
                lineToRelative(-4f, 4f)
                verticalLineTo(5f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, -2f)
                horizontalLineToRelative(14f)
                arcToRelative(2f, 2f, 0f, false, true, 2f, 2f)
                close()
            }
        }.build()

    // Lucide Check
    val Check: ImageVector
        get() = ImageVector.Builder(
            name = "Check",
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
                moveTo(20f, 6f)
                lineTo(9f, 17f)
                lineTo(4f, 12f)
            }
        }.build()

    // Lucide MoreVertical
    val MoreVertical: ImageVector
        get() = ImageVector.Builder(
            name = "MoreVertical",
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
                moveTo(12f, 12f)
                moveToRelative(-1f, 0f)
                arcToRelative(1f, 1f, 0f, true, true, 2f, 0f)
                arcToRelative(1f, 1f, 0f, true, true, -2f, 0f)
                moveTo(12f, 5f)
                moveToRelative(-1f, 0f)
                arcToRelative(1f, 1f, 0f, true, true, 2f, 0f)
                arcToRelative(1f, 1f, 0f, true, true, -2f, 0f)
                moveTo(12f, 19f)
                moveToRelative(-1f, 0f)
                arcToRelative(1f, 1f, 0f, true, true, 2f, 0f)
                arcToRelative(1f, 1f, 0f, true, true, -2f, 0f)
            }
        }.build()

    // Lucide Edit2
    val Edit2: ImageVector
        get() = ImageVector.Builder(
            name = "Edit2",
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
                moveTo(17f, 3f)
                arcToRelative(2.828f, 2.828f, 0f, true, true, 4f, 4f)
                lineTo(7.5f, 20.5f)
                lineTo(2f, 22f)
                lineToRelative(1.5f, -5.5f)
                close()
            }
        }.build()

    // Lucide PinOff
    val PinOff: ImageVector
        get() = ImageVector.Builder(
            name = "PinOff",
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
                moveTo(2f, 2f)
                lineTo(22f, 22f)
                moveTo(12f, 7f)
                verticalLineTo(5f)
                moveTo(15f, 5f)
                lineTo(13.43f, 6.57f)
                moveTo(11f, 11f)
                lineTo(4f, 11f)
                lineTo(11f, 18f)
                verticalLineToRelative(5f)
                lineTo(13f, 21f)
                lineTo(13f, 18f)
                lineTo(14.57f, 16.43f)
                moveTo(18f, 13f)
                verticalLineTo(8f)
                horizontalLineToRelative(-5f)
            }
        }.build()

    // Lucide HelpCircle
    val HelpCircle: ImageVector
        get() = ImageVector.Builder(
            name = "HelpCircle",
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
                moveTo(12f, 22f)
                curveToRelative(5.523f, 0f, 10f, -4.477f, 10f, -10f)
                reflectiveCurveTo(17.523f, 2f, 12f, 2f)
                reflectiveCurveTo(2f, 6.477f, 2f, 12f)
                reflectiveCurveToRelative(4.477f, 10f, 10f, 10f)
                close()
                moveTo(9.09f, 9f)
                arcToRelative(3f, 3f, 0f, false, true, 5.83f, 1f)
                curveToRelative(0f, 2f, -3f, 3f, -3f, 3f)
                moveTo(12f, 17f)
                horizontalLineToRelative(0.01f)
            }
        }.build()

    // Lucide Smartphone
    val Smartphone: ImageVector
        get() = ImageVector.Builder(
            name = "Smartphone",
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
                // ["rect", { width: "14", height: "20", x: "5", y: "2", rx: "2", ry: "2" }]
                moveTo(7f, 2f)
                lineTo(17f, 2f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 19f, y1 = 4f)
                lineTo(19f, 20f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 17f, y1 = 22f)
                lineTo(7f, 22f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 5f, y1 = 20f)
                lineTo(5f, 4f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 7f, y1 = 2f)
                close()
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // ["path", { d: "M12 18h.01" }]
                moveTo(12f, 18f)
                horizontalLineToRelative(0.01f)
            }
        }.build()

    // Lucide CheckCircle (CircleCheck)
    val CheckCircle: ImageVector
        get() = ImageVector.Builder(
            name = "CheckCircle",
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
                // ["circle", { cx: "12", cy: "12", r: "10" }]
                moveTo(12f, 2f)
                arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 22f, y1 = 12f)
                arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 12f, y1 = 22f)
                arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 2f, y1 = 12f)
                arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 12f, y1 = 2f)
                close()
            }
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // ["path", { d: "m9 12 2 2 4-4" }]
                moveTo(9f, 12f)
                lineToRelative(2f, 2f)
                lineToRelative(4f, -4f)
            }
        }.build()
}
