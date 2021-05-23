package com.cendekia.movieapp.di

import com.cendekia.movieapp.core.domain.usecase.WatchesInteractor
import com.cendekia.movieapp.core.domain.usecase.WatchesUseCase
import com.cendekia.movieapp.detail.DetailWatchesViewModel
import com.cendekia.movieapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<WatchesUseCase> { WatchesInteractor(get()) }
}


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailWatchesViewModel(get()) }
}