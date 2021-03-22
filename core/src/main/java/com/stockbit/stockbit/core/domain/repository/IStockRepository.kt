package com.stockbit.stockbit.core.domain.repository

import com.stockbit.stockbit.core.data.source.Resource
import com.stockbit.stockbit.core.data.source.remote.network.ApiResponse
import com.stockbit.stockbit.core.data.source.remote.response.CryptoData
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface IStockRepository {

    fun  getCrypto(page: Int): Flowable<ApiResponse<List<CryptoData>>>
}