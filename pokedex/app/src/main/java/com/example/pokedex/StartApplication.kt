package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.KoinManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StartApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(applicationContext)
            modules(KoinManager.getModulosAplicacao())
        }
    }
}