package com.cendekia.movieapp

import android.app.Application
import com.cendekia.movieapp.core.di.databaseModule
import com.cendekia.movieapp.core.di.networkModule
import com.cendekia.movieapp.core.di.repositoryModule
import com.cendekia.movieapp.di.useCaseModule
import com.cendekia.movieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule

                )
            )
        }
    }
}