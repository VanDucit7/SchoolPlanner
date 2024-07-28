package data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import data.database.model.TermEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TermDao {

    @Upsert
    suspend fun upsert(termEntity: TermEntity)

    @Delete
    suspend fun delete(termEntity: TermEntity)

    @Query("SELECT * FROM Term")
    fun getAllTerm(): Flow<List<TermEntity>>

}