package com.cendekia.movieapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cendekia.movieapp.core.data.source.local.entity.WatchesEntity

@Database(entities = [WatchesEntity::class], version = 2, exportSchema = false)

abstract class WatchesDatabase : RoomDatabase() {

    abstract fun watchesDao(): WatchesDao

}