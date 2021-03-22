package com.stockbit.stockbit.di

import com.stockbit.stockbit.core.domain.usecase.StockInteractor
import com.stockbit.stockbit.core.domain.usecase.StockUseCase
import com.stockbit.stockbit.ui.login.LoginViewModel
import com.stockbit.stockbit.ui.watch.WatchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<StockUseCase> { StockInteractor(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { WatchViewModel(get()) }
}