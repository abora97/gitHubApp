package com.abora.githubtask.app

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.abora.githubtask.di.module.classesModule
import com.abora.githubtask.di.module.networkModule
import com.abora.githubtask.di.module.preferencesModule
import com.abora.githubtask.di.module.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class Application : Application() {



    override fun onCreate() {
        super.onCreate()

        setupExceptionHandler()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Application)
            modules(networkModule, preferencesModule, viewModelModule, classesModule)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }
    private fun setupExceptionHandler(){
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
//                    uncaughtException(Looper.getMainLooper().thread, e)
                    Log.e("AppCrash",e.toString())
                }
            }
        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
//            uncaughtException(t, e)
            Log.e("AppCrash",e.toString())
        }
    }

}