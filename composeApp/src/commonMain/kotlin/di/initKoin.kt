package di

import di.data.platformDataModule
import di.data.sharedDataModule
import di.presentation.presentationModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformDataModule,
            sharedDataModule,
            presentationModules
        )
    }
}