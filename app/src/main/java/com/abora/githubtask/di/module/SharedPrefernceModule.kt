package com.abora.githubtask.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val preferencesModule  = module {
    single { provideSettingsPreferences(androidApplication()) }
}
private const val PREFERENCES_FILE_KEY = "com.preferences.data"

private fun provideSettingsPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)