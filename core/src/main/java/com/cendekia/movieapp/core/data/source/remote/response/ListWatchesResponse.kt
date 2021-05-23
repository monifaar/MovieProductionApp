package com.cendekia.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListWatchesResponse(

    @field:SerializedName("results")
    val box: List<WatchesResponse>
)