package com.cendekia.movieapp.core.data.source.local

import com.cendekia.movieapp.core.data.source.local.entity.WatchesEntity
import com.cendekia.movieapp.core.data.source.local.room.WatchesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val watchesDao: WatchesDao) {

    fun getAllWatches(): Flow<List<WatchesEntity>> = watchesDao.getAllWatches()
    fun getFavoriteWatches(): Flow<List<WatchesEntity>> = watchesDao.getFavoriteWatches()
    suspend fun insertWatches(watchesList: List<WatchesEntity>) = watchesDao.insertWatches(watchesList)
    fun setFavoriteWatches(watches: WatchesEntity, newState: Boolean) {

        watches.isFavorite = newState
        watchesDao.updateFavoriteWatches(watches)
    }
}