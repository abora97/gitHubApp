package com.abora.githubtask.di.module

import com.abora.githubtask.data.remote.reporsitory.MainRepository
import org.koin.dsl.module

val classesModule = module {
    single { MainRepository(get()) }
}