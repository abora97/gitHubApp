package com.abora.githubtask.di.module


import com.abora.githubtask.screens.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }


}
