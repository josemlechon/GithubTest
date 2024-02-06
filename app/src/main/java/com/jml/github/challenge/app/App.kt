package com.jml.github.challenge.app

import android.app.Application
import com.jml.github.challenge.data.di.network
import com.jml.github.challenge.di.dataModule
import com.jml.github.challenge.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoinDI()
    }

    private fun startKoinDI(){
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(listOf(
                uiModule,
                network,
                dataModule
            ))
        }
    }
}