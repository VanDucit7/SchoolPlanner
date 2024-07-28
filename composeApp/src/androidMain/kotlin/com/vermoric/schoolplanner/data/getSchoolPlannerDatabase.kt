package com.vermoric.schoolplanner.data

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.SchoolPlannerDatabase

fun getSchoolPlannerDatabase(context: Context): SchoolPlannerDatabase {
    val dbFile = context.getDatabasePath("SchoolPlanner.db")
    return Room.databaseBuilder<SchoolPlannerDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}