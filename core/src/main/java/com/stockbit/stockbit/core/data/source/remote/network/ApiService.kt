package com.stockbit.stockbit.core.data.source.remote.network

import com.stockbit.stockbit.core.data.source.remote.response.CryptoResponse
import io.reactivex.Flowable
import retrofit2.http.*

interface ApiService {

    @GET("top/totaltoptiervolfull?limit=50&tsym=USD")
    fun getCrypto(
        @Query("page") page: String,
        @Query("api_key") api_key: String
    ): Flowable<CryptoResponse>
}