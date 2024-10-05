package com.serdar.barchart.util

import androidx.compose.ui.unit.Dp

//Change this after
fun calculateLeftAreaWidth(verticalAxisValues: List<Float>, verticalAxisSize: Dp): Float {
    val maxValue = verticalAxisValues.maxOrNull() ?: 0f
    val maxLength = maxValue.toInt().toString().length

    return maxLength * 1.75f
}

fun calculateBarXPosition(
    index: Int, barWidth: Float, paddingBetweenBarsPx: Float, leftAreaWidth: Float
): Float {
    return (barWidth + paddingBetweenBarsPx) * index + paddingBetweenBarsPx + leftAreaWidth / 2
}

fun calculateBarHeight(
    completedValue: Float, maxTargetValue: Float, verticalAxisLength: Float
): Float {
    return (completedValue / maxTargetValue).coerceIn(0f, 1f) * verticalAxisLength
}
