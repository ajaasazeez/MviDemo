package com.example.mymviapp

import android.app.Application
import com.example.mymviapp.di.databaseModule
import com.example.mymviapp.di.mainModule
import com.example.mymviapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@NewsApplication)
            // declare modules
            modules(networkModule, databaseModule, mainModule)
        }
    }
}