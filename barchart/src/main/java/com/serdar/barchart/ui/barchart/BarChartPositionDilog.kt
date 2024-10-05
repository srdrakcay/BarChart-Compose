package com.serdar.barchart.ui.barchart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun BarChartPositionDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    positionX: Int = 0,
    positionY: Int = 0,
    value: Int,
) {
    if (isVisible) {
        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(positionX, positionY),
            onDismissRequest = onDismiss
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.background,
                tonalElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bar Chart",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                    Row {
                        Text(text = "Value : $value", style = MaterialTheme.typography.bodySmall)

                    }


                }
            }

        }
    }
}