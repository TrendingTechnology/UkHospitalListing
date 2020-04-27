package uk.cookpad.ukhospital.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uk.cookpad.ukhospital.ui.home.HomeViewModel

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
}