package di.presentation

import org.koin.dsl.module
import ui.presentation.configterm.ConfigTermViewModel

val presentationModules = module {

    factory<ConfigTermViewModel> {
        ConfigTermViewModel(termRepository = get())
    }

}