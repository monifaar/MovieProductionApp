package com.cendekia.movieapp.core.domain.usecase

import com.cendekia.movieapp.core.domain.model.Watches
import com.cendekia.movieapp.core.domain.repository.IWatchesRepository

class WatchesInteractor(private val watchesRepository: IWatchesRepository) : WatchesUseCase {

    override fun getAllWatches() = watchesRepository.getAllWatches()
    override fun getFavoriteWatches() = watchesRepository.getFavoriteWatches()
    override fun setFavoriteWatches(watches: Watches, state: Boolean) =
        watchesRepository.setFavoriteWatches(watches, state)
}