package com.serdar.barchart_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.serdar.barchart.data.BarChartData
import com.serdar.barchart.ui.barchart.BarChart
import com.serdar.barchart.ui.barchart.BarChartPositionDialog
import com.serdar.barchart.util.getShortDayOfWeek
import com.serdar.barchart_compose.ui.theme.BarChartComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BarChartComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var showDialog by remember { mutableStateOf(false) }
                    val derivedShowDialog by derivedStateOf { showDialog }
                    var positionX by remember { mutableIntStateOf(0) }
                    var positionY by remember { mutableIntStateOf(0) }
                    var barChartData by remember {
                        mutableStateOf(
                            BarChartData(
                                0f, 0f, listOf(), null,
                            )
                        )
                    }
                    BarChart(modifier = Modifier.padding(innerPadding), barChartData = listOf(
                        BarChartData(
                            target = 300f,
                            completed = 220f,
                            label = getShortDayOfWeek("2024-10-11"),
                            color = listOf(Color.Red, Color.Red)
                        ), BarChartData(
                            target = 200f,
                            completed = 250f,
                            label = getShortDayOfWeek("2024-10-10"),
                            color = listOf(Color.Red, Color.Blue)
                        ), BarChartData(
                            target = 100f,
                            completed = 120f,
                            label = getShortDayOfWeek("2024-10-09"),
                            color = listOf(Color.Red, Color.Blue)
                        ), BarChartData(
                            target = 400f,
                            completed = 420f,
                            label = getShortDayOfWeek("2024-10-08"),
                            color = listOf(Color.Red, Color.Blue)
                        ), BarChartData(
                            target = 400f,
                            completed = 120f,
                            label = getShortDayOfWeek("2024-10-07"),
                            color = listOf(Color.Red, Color.Blue)
                        ), BarChartData(
                            target = 400f,
                            completed = 220f,
                            label = getShortDayOfWeek("2024-10-06"),
                            color = listOf(Color.Red, Color.Blue)
                        ), BarChartData(
                            target = 400f,
                            completed = 220f,
                            label = getShortDayOfWeek("2024-10-06"),
                            color = listOf(Color.Red, Color.Blue)
                        )

                    ), horizontalAxisLabelColor = Color.Blue, onBarClick = { label ->
                        if (!derivedShowDialog) {
                            barChartData = label
                            showDialog = true
                        }

                    }, onGesturePosition = { first, second ->
                        positionX = first.toInt()
                        positionY = second.toInt()
                    })
                    if (showDialog) {
                        BarChartPositionDialog(
                            isVisible = showDialog,
                            onDismiss = { showDialog = false },
                            positionX = positionX + 100,
                            positionY = positionY + 100,
                            value = barChartData.completed.toInt(),
                        )

                    }
                }

            }

        }
    }
}

