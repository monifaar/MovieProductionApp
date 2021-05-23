package com.cendekia.movieapp.core.data.source.remote.network

import com.cendekia.movieapp.core.data.source.remote.response.ListWatchesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")

    suspend fun getList(@Query("api_key") q: String): ListWatchesResponse
}
