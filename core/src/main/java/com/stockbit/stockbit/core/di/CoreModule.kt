package com.stockbit.stockbit.core.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.stockbit.stockbit.core.data.source.StockRepository
import com.stockbit.stockbit.core.data.source.remote.RemoteDataSource
import com.stockbit.stockbit.core.data.source.remote.network.ApiService
import com.stockbit.stockbit.core.data.source.shared.SessionManager
import com.stockbit.stockbit.core.domain.repository.IStockRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(
                ChuckerInterceptor.Builder(get())
                    .collector(ChuckerCollector(get()))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://min-api.cryptocompare.com/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val sessionModule = module {
    factory { SessionManager(get()) }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IStockRepository> {
        StockRepository(
            get()
        )
    }
}