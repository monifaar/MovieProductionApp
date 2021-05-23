package com.cendekia.movieapp.core.data.source.local.room

import androidx.room.*
import com.cendekia.movieapp.core.data.source.local.entity.WatchesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchesDao {

    @Query("SELECT * FROM watches")
    fun getAllWatches(): Flow<List<WatchesEntity>>

    @Query("SELECT * FROM watches where isFavorite = 1")
    fun getFavoriteWatches(): Flow<List<WatchesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatches(watches: List<WatchesEntity>)

    @Update
    fun updateFavoriteWatches(watches: WatchesEntity)
}
