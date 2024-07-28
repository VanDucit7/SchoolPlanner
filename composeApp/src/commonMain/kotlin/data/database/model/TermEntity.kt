package data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import domain.model.Term
import domain.usecase.InstantConverters

@Entity("Term")
data class TermEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    var startDate: Long,
    var endDate: Long
)

fun TermEntity.asExternalModel() = Term(
    name = name,
    startDate = InstantConverters().fromTimestamp(startDate),
    endDate = InstantConverters().fromTimestamp(endDate)
)
