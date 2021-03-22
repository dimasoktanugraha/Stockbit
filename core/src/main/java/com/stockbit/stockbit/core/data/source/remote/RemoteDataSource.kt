package com.stockbit.stockbit.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.stockbit.stockbit.core.data.source.remote.network.ApiResponse
import com.stockbit.stockbit.core.data.source.remote.network.ApiService
import com.stockbit.stockbit.core.data.source.remote.response.CryptoData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.HttpException

class RemoteDataSource(private  val apiService: ApiService) {

//    suspend fun getCrypto(page: Int): Flow<ApiResponse<List<CryptoData>>>{
//        return flow{
//            try {
//                Log.d("CRYPTO", "remote")
//                emit(ApiResponse.Loading)
//                val response = apiService.getCrypto(
//                    page.toString(),
//                    "8859e785c741d674db60558d62ac950306462ff6a452defaee5314250ba775e2"
//                )
//                val dataArray = response.Data
//                if (dataArray.isNotEmpty()){
//                    Log.d("CRYPTO", "remote success")
//                    emit(ApiResponse.Success(response.Data))
//                } else {
//                    Log.d("CRYPTO", "remote empty")
//                    emit(ApiResponse.Empty)
//                }
//            }catch (e : Exception){
//                Log.d("CRYPTO", "remote error "+e.toString())
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("CRYPTO", e.toString())
//            }
//        }.flowOn(Dispatchers.IO)
//    }

    @SuppressLint("CheckResult")
    fun getCrypto(page: Int): Flowable<ApiResponse<List<CryptoData>>>{
        val resultData = PublishSubject.create<ApiResponse<List<CryptoData>>>()
        resultData.onNext(ApiResponse.Loading)

        apiService.getCrypto(
            page.toString(),
            "8859e785c741d674db60558d62ac950306462ff6a452defaee5314250ba775e2")
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                if (response.Data.isNotEmpty()){
                    resultData.onNext(ApiResponse.Success(response.Data))
                }else{
                    resultData.onNext(ApiResponse.Empty)
                }
            }, { error ->
                error.printStackTrace()
                if (error is HttpException) {
                    resultData.onNext(ApiResponse.Error(error.message()))
                } else {
                    resultData.onNext(ApiResponse.Error(error.message.toString()))
                }
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}