package com.stockbit.stockbit.core.data.source

import android.util.Log
import com.stockbit.stockbit.core.data.source.remote.RemoteDataSource
import com.stockbit.stockbit.core.data.source.remote.network.ApiResponse
import com.stockbit.stockbit.core.data.source.remote.response.CryptoData
import com.stockbit.stockbit.core.domain.repository.IStockRepository
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class StockRepository(
    private val remoteDataSource: RemoteDataSource
): IStockRepository {

    override fun getCrypto(page: Int): Flowable<ApiResponse<List<CryptoData>>> =
        remoteDataSource.getCrypto(page)




//    override fun getCrypto(page: Int): Flow<Resource<List<CryptoData>>> =
//        object : NetworkBoundResource<List<CryptoData>, List<CryptoData>>() {
//            override fun loadFromDB(): Flow<List<CryptoData>> {
//                val list = listOf<CryptoData>()
//                return list
//            }
//
//            override fun shouldFetch(data: List<CryptoData>?): Boolean = true
//
//            override suspend fun createCall(): Flow<ApiResponse<List<CryptoData>>> =
//                remoteDataSource.getCrypto(page)
//
//            override suspend fun saveCallResult(data: List<CryptoData>) {
//            }
//        }.asFlow()
}