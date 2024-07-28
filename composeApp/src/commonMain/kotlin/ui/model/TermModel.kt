package ui.model

import domain.model.Term
import domain.usecase.formatInstantToDateString
import kotlinx.datetime.Clock

data class TermModel(
    val position: Int,
    val name: String,
    var startDate: String = formatInstantToDateString(Clock.System.now()),
    var endDate: String = formatInstantToDateString(Clock.System.now())
)

fun Term.asExternalModel() = TermModel(
    0,
    name = name,
    startDate = formatInstantToDateString(startDate ?: Clock.System.now()),
    endDate = formatInstantToDateString(endDate ?: Clock.System.now())
)