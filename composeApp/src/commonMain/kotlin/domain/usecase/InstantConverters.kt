package domain.usecase

import kotlinx.datetime.Instant

class InstantConverters {

    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(it) }
    }

    fun dateToTimestamp(date: Instant?): Long? {
        return date?.toEpochMilliseconds()
    }
}