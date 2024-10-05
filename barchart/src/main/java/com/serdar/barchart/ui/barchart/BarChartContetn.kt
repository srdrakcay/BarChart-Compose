package com.serdar.barchart.ui.barchart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.serdar.barchart.data.BarChartData
import com.serdar.barchart.util.calculateBarHeight
import com.serdar.barchart.util.calculateBarXPosition
import com.serdar.barchart.util.calculateLeftAreaWidth
import com.serdar.barchart.util.dpToPx
import com.serdar.barchart.util.drawBarBackground
import com.serdar.barchart.util.drawBarLabel
import com.serdar.barchart.util.drawBarValue
import com.serdar.barchart.util.drawFilledBar
import com.serdar.barchart.util.isTouched

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    barChartData: List<BarChartData>,
    horizontalAxisLabelColor: Color = Color.Red,
    horizontalAxisSize: Dp = 12.dp,
    verticalAxisSize: Dp = 12.dp,
    paddingBetweenBars: Dp = 12.dp,
    cardPadding: Dp = 12.dp,
    cornerRadius: Dp = 8.dp,
    onBarClick: (BarChartData) -> Unit,
    onGesturePosition: (Float, Float) -> Unit
) {
    val paddingBetweenBarsPx = dpToPx(paddingBetweenBars)
    val cornerRadiusPx = dpToPx(cornerRadius)
    var touchX by remember { mutableFloatStateOf(-1f) }
    val leftAreaWidth = calculateLeftAreaWidth(listOf(), verticalAxisSize)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(cardPadding),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column {
            Text(
                text = "BarChart",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = modifier.padding(15.dp))
            Canvas(modifier = modifier
                .aspectRatio(2f)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        touchX = offset.x
                        onGesturePosition(offset.x, offset.y)
                    }
                }) {
                val bottomAreaHeight = horizontalAxisSize.toPx()
                val verticalAxisLength = size.height - bottomAreaHeight
                val canvasWidth = size.width

                val totalBarPadding = paddingBetweenBarsPx * (barChartData.size + 1)
                val availableWidth = canvasWidth - totalBarPadding - leftAreaWidth
                val barWidth = (availableWidth / barChartData.size).coerceAtLeast(0f)

                val maxTargetValue = barChartData.maxOf { it.target }

                var clickedBarEntity: BarChartData? = null

                barChartData.forEachIndexed { index, entity ->
                    val xPosition =
                        calculateBarXPosition(index, barWidth, paddingBetweenBarsPx, leftAreaWidth)
                    val barHeightInPixel =
                        calculateBarHeight(entity.completed, maxTargetValue, verticalAxisLength)
                    drawBarBackground(
                        xPosition, verticalAxisLength, barWidth, cornerRadiusPx, verticalAxisLength
                    )
                    drawFilledBar(
                        xPosition,
                        barHeightInPixel,
                        barWidth,
                        cornerRadiusPx,
                        verticalAxisLength,
                        entity.color
                    )
                    drawBarValue(
                        xPosition,
                        entity.completed,
                        barWidth,
                        verticalAxisLength,
                        barHeightInPixel,
                        horizontalAxisSize,
                        horizontalAxisLabelColor,
                    )
                    drawBarLabel(
                        xPosition,
                        entity.label,
                        barWidth,
                        verticalAxisLength,
                        horizontalAxisSize,
                        horizontalAxisLabelColor
                    )
                    if (isTouched(xPosition, xPosition + barWidth, touchX)) {
                        clickedBarEntity = entity
                    }
                }

                clickedBarEntity?.let {
                    onBarClick(it)
                    touchX = -1f
                }
            }
            Spacer(modifier = modifier.padding(10.dp))
        }
    }
}





