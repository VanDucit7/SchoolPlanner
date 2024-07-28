package data

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.SchoolPlannerDatabase
import platform.Foundation.NSHomeDirectory

fun getSchoolPlannerDatabase(): SchoolPlannerDatabase {
    val dbFile = NSHomeDirectory() + "/SchoolPlanner.db"
    return Room.databaseBuilder<SchoolPlannerDatabase>(
        name = dbFile,
        factory = { SchoolPlannerDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}