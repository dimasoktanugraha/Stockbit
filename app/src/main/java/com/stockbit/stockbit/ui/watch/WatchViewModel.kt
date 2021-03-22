package com.stockbit.stockbit.ui.watch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.stockbit.stockbit.core.data.source.remote.network.ApiResponse
import com.stockbit.stockbit.core.data.source.remote.response.CryptoData
import com.stockbit.stockbit.core.domain.usecase.StockUseCase
import kotlinx.coroutines.flow.Flow

class WatchViewModel(private val stockUseCase: StockUseCase): ViewModel() {

    fun getCrypto(page: Int): LiveData<ApiResponse<List<CryptoData>>> = LiveDataReactiveStreams.fromPublisher( stockUseCase.getCrypto(page))
}