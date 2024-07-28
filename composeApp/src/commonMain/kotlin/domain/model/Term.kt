package domain.model

import kotlinx.datetime.Instant

data class Term(
    val name: String,
    var startDate: Instant?,
    var endDate: Instant?
)