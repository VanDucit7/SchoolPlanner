package domain.usecase

import data.repository.TermRepository
import domain.model.Term
import kotlinx.coroutines.flow.Flow

class ConfigTermUseCase(private val repo: TermRepository) {

    fun getAllTerm(): Flow<List<Term>> = repo.getAllTerm()
}