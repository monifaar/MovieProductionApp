package com.cendekia.movieapp.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cendekia.movieapp.core.domain.usecase.WatchesUseCase

class FavoriteWatchesViewModel(watchesUseCase: WatchesUseCase) : ViewModel() {
    val favoriteWatches = watchesUseCase.getFavoriteWatches().asLiveData()
}

