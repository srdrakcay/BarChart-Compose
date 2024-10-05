package com.serdar.barchart.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
internal fun dpToPx(value: Dp): Float = LocalDensity.current.run { value.toPx() }

