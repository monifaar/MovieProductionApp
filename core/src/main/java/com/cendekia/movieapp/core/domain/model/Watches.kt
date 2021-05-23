package com.cendekia.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Watches(

    val movieId: String,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_data: String,
    val isFavorite: Boolean

) : Parcelable