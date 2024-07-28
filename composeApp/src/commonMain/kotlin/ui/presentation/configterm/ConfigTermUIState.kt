package ui.presentation.configterm

import ui.model.TermModel

data class ConfigTermUIState(
    val terms: List<TermModel> = listOf(),
    val editTerm: TermModel = TermModel(0, "", "", "")
)