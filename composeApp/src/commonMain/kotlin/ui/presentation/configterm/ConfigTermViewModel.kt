package ui.presentation.configterm

import androidx.lifecycle.ViewModel
import data.repository.TermRepository
import domain.usecase.getOrdinal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.model.TermModel
import ui.model.asExternalModel

class ConfigTermViewModel(
    private val termRepository: TermRepository,
    viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : ViewModel(viewModelScope) {

    /**
     * Cupcake state for this order
     */
    private val _uiState = MutableStateFlow(ConfigTermUIState())
    val uiState: StateFlow<ConfigTermUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        termRepository.getAllTerm().collect { values ->
            val termModels = values.map { it.asExternalModel() }
            _uiState.update { currentState ->
                currentState.copy(
                    terms = termModels
                )
            }
        }
    }

    fun addTerm(term: String) {
        _uiState.update { currentState ->
            val terms = currentState.terms.toMutableList()
            val name = "${getOrdinal(terms.size + 1)} $term"
            terms += TermModel(terms.size + 1, name)
            currentState.copy(
                terms = terms
            )
        }
    }

    fun updateTerm(
        name: String,
        startDate: String,
        endDate: String
    ) {

    }

    fun saveTerm() {

    }

    fun editTerm(index: Int, term: TermModel) {
        _uiState.update { currentState ->
            currentState.copy(
                editTerm = TermModel(index, term.name, term.startDate, term.endDate)
            )
        }
    }
}