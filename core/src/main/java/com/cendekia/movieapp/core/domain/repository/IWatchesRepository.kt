package com.cendekia.movieapp.core.domain.repository

import com.cendekia.movieapp.core.data.Resource
import com.cendekia.movieapp.core.domain.model.Watches
import kotlinx.coroutines.flow.Flow

interface IWatchesRepository {

    fun getAllWatches(): Flow<Resource<List<Watches>>>
    fun getFavoriteWatches(): Flow<List<Watches>>
    fun setFavoriteWatches(watches: Watches, state: Boolean)

}