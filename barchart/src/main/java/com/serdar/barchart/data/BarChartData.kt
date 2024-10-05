package com.serdar.barchart.data

import androidx.compose.ui.graphics.Color

data class BarChartData(
    val target: Float,
    val completed: Float,
    val color: List<Color>,
    val label: String? = null,
)
