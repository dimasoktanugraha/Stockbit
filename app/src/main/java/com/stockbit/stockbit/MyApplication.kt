package com.stockbit.stockbit

import android.app.Application
import com.stockbit.stockbit.core.di.networkModule
import com.stockbit.stockbit.core.di.repositoryModule
import com.stockbit.stockbit.core.di.sessionModule
import com.stockbit.stockbit.di.useCaseModule
import com.stockbit.stockbit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    sessionModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}