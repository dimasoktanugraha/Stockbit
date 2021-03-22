package com.stockbit.stockbit.core.domain.usecase

import android.util.Log
import com.stockbit.stockbit.core.data.source.StockRepository
import com.stockbit.stockbit.core.data.source.remote.network.ApiResponse
import com.stockbit.stockbit.core.data.source.remote.response.CryptoData
import com.stockbit.stockbit.core.domain.repository.IStockRepository
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

class StockInteractor(private  val stockRepository: IStockRepository): StockUseCase{

    override fun getCrypto(page: Int): Flowable<ApiResponse<List<CryptoData>>> {
        Log.d("CRYPTO", "interactor")
        return stockRepository.getCrypto(page)
    }

}