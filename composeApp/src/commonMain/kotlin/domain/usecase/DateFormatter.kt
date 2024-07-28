package domain.usecase

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun formatInstantToDateString(
    instant: Instant,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): String {
    val localDateTime = instant.toLocalDateTime(timeZone)
    return "${localDateTime.dayOfMonth.toString().padStart(2, '0')}/" +
            "${localDateTime.monthNumber.toString().padStart(2, '0')}/" +
            "${localDateTime.year}"
}

fun convertToKotlinxInstant(dateString: String): Instant {
    val parts = dateString.split("/")
    if (parts.size != 3) {
        throw IllegalArgumentException("Date format should be dd/MM/yyyy")
    }
    val day = parts[0].toInt()
    val month = parts[1].toInt()
    val year = parts[2].toInt()
    val localDate = LocalDate(year, month, day)
    val localDateTime = LocalDateTime(localDate.year, localDate.month, localDate.dayOfMonth, 0, 0)
    return localDateTime.toInstant(TimeZone.UTC)
}