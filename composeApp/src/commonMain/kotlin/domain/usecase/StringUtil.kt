package domain.usecase

import androidx.compose.ui.text.intl.Locale


fun getOrdinal(number: Int, locale: Locale = Locale.current): String {
    val vietnameseLocale = Locale("vi-VN")
    return if (locale == vietnameseLocale) {
        "thứ $number"
    } else {
        if (number % 100 in 11..13) {
            "${number}th"
        } else {
            when (number % 10) {
                1 -> "${number}st"
                2 -> "${number}nd"
                3 -> "${number}rd"
                else -> "${number}th"
            }
        }
    }
}