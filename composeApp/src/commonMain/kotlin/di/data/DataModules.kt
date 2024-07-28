package di.data

import data.database.SchoolPlannerDatabase
import data.database.dao.TermDao
import data.repository.TermRepository
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformDataModule: Module

val sharedDataModule = module {

    factory<TermDao> {
        val database = get<SchoolPlannerDatabase>()
        database.termDao()
    }

    single<TermRepository> { TermRepository(get()) }

}