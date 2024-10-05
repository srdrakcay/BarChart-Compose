package com.serdar.barchart.util

import android.graphics.Paint
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

fun DrawScope.drawBarBackground(
    xPosition: Float,
    barHeightInPixel: Float,
    barWidth: Float,
    cornerRadiusPx: Float,
    verticalAxisLength: Float
) {
    drawRoundRect(
        color = Color.Gray.copy(alpha = 0.4f),
        topLeft = Offset(xPosition, verticalAxisLength - barHeightInPixel),
        size = Size(barWidth, barHeightInPixel),
        cornerRadius = CornerRadius(cornerRadiusPx)
    )
}

fun DrawScope.drawFilledBar(
    xPosition: Float,
    barHeightInPixel: Float,
    barWidth: Float,
    cornerRadiusPx: Float,
    verticalAxisLength: Float,
    barColors: List<Color>
) {
    drawRoundRect(
        brush = Brush.linearGradient(colors = barColors),
        topLeft = Offset(xPosition, verticalAxisLength - barHeightInPixel),
        size = Size(barWidth, barHeightInPixel),
        cornerRadius = CornerRadius(cornerRadiusPx)
    )
}

fun DrawScope.drawBarValue(
    xPosition: Float,
    completedValue: Float,
    barWidth: Float,
    maxBarHeightInPixel: Float,
    barHeightInPixel: Float,
    horizontalAxisSize: Dp,
    horizontalAxisLabelColor: Color
) {
    drawContext.canvas.nativeCanvas.apply {
        drawText(completedValue.toString(),
            xPosition + barWidth / 2,
            maxBarHeightInPixel - barHeightInPixel - 5.sp.toPx(),
            Paint().apply {
                textSize = horizontalAxisSize.toPx()
                color = horizontalAxisLabelColor.toArgb()
                textAlign = Paint.Align.CENTER
            })
    }
}

fun DrawScope.drawBarLabel(
    xPosition: Float,
    label: String?,
    barWidth: Float,
    verticalAxisLength: Float,
    horizontalAxisSize: Dp,
    horizontalAxisLabelColor: Color
) {
    label?.let {
        drawContext.canvas.nativeCanvas.apply {
            drawText(it,
                xPosition + barWidth / 2,
                verticalAxisLength + horizontalAxisSize.toPx(),
                Paint().apply {
                    textSize = horizontalAxisSize.toPx()
                    color = horizontalAxisLabelColor.toArgb()
                    textAlign = Paint.Align.CENTER
                })
        }
    }
}

