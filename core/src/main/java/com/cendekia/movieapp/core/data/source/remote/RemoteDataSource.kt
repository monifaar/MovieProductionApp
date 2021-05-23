package com.cendekia.movieapp.core.data.source.remote

import android.util.Log
import com.cendekia.movieapp.core.data.source.remote.network.ApiResponse
import com.cendekia.movieapp.core.data.source.remote.network.ApiService
import com.cendekia.movieapp.core.data.source.remote.response.WatchesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllWatches(): Flow<ApiResponse<List<WatchesResponse>>> {

        return flow {
            try {
                val response = apiService.getList("e1b5fc4de68f0cab2c88dc98d7f8fe28")

                val dataArray = response.box

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.box))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

