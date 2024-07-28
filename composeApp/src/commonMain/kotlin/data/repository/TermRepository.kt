package data.repository

import data.database.dao.TermDao
import data.database.model.TermEntity
import data.database.model.asExternalModel
import domain.model.Term
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TermRepository(
    private val termDao: TermDao
) {
    fun getAllTerm(): Flow<List<Term>> =
        termDao.getAllTerm().map { it.map(TermEntity::asExternalModel) }
}