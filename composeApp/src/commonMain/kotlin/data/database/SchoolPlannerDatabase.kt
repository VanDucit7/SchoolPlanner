package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.database.dao.TermDao
import data.database.model.TermEntity

@Database(
    entities = [TermEntity::class],
    version = 1
)
abstract class SchoolPlannerDatabase : RoomDatabase() {

    abstract fun termDao(): TermDao

}