package com.cendekia.movieapp.detail

import androidx.lifecycle.ViewModel
import com.cendekia.movieapp.core.domain.model.Watches
import com.cendekia.movieapp.core.domain.usecase.WatchesUseCase

class DetailWatchesViewModel(private val watchesUseCase: WatchesUseCase) : ViewModel() {
    fun setFavoritesWatches(watches: Watches, newStatus: Boolean) =
        watchesUseCase.setFavoriteWatches(watches, newStatus)
}

