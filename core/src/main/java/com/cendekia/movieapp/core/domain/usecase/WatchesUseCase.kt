package com.cendekia.movieapp.core.domain.usecase

import com.cendekia.movieapp.core.data.Resource
import com.cendekia.movieapp.core.domain.model.Watches
import kotlinx.coroutines.flow.Flow

interface WatchesUseCase {
    fun getAllWatches(): Flow<Resource<List<Watches>>>
    fun getFavoriteWatches(): Flow<List<Watches>>
    fun setFavoriteWatches(watches: Watches, state: Boolean)
}