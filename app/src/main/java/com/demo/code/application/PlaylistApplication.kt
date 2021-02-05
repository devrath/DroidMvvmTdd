package com.demo.code.application

import android.app.Application
import com.demo.code.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PlaylistApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PlaylistApplication)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule, serviceModule))
        }
    }

}