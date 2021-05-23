package com.cendekia.movieapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watches")
data class WatchesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var movieId: String,

    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "poster_path")
    var poster_path: String,
    @ColumnInfo(name = "release_data")
    var release_data: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
