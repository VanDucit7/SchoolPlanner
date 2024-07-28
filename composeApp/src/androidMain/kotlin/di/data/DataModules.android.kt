package di.data

import com.vermoric.schoolplanner.data.getSchoolPlannerDatabase
import data.database.SchoolPlannerDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformDataModule = module {
    single<SchoolPlannerDatabase> { getSchoolPlannerDatabase(androidContext()) }
}
