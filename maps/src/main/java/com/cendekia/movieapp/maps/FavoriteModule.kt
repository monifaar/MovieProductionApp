package com.cendekia.movieapp.maps.di

import com.cendekia.movieapp.maps.FavoriteWatchesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    viewModel { FavoriteWatchesViewModel(get()) }
}