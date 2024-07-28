package di.data

import data.database.SchoolPlannerDatabase
import data.getSchoolPlannerDatabase
import org.koin.dsl.module

actual val platformDataModule = module {
    single<SchoolPlannerDatabase> { getSchoolPlannerDatabase() }
}