package com.stockbit.stockbit.core.data.source.remote.response

data class CryptoResponse(
    val Data: List<CryptoData>,
    val Message: String,
    val MetaData: MetaData,
    val Type: Int
)