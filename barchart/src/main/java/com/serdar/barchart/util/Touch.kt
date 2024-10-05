package com.serdar.barchart.util

fun isTouched(startX: Float, endX: Float, touchX: Float?): Boolean {
    return touchX != null && touchX in startX..endX
}