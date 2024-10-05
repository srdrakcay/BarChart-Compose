package com.serdar.barchart.util

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun getShortDayOfWeek(dateString: String, locale: Locale = Locale.getDefault()): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (dateString.isNotEmpty()) {
            val date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            date.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)
        } else {
            " "
        }
    } else {
        " "
    }
}