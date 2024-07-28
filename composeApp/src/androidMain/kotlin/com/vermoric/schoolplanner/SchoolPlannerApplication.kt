package com.vermoric.schoolplanner

import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class SchoolPlannerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SchoolPlannerApplication)
        }
    }
}