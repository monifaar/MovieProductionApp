package com.cendekia.movieapp.core.data

import com.cendekia.movieapp.core.data.source.local.LocalDataSource
import com.cendekia.movieapp.core.data.source.remote.RemoteDataSource
import com.cendekia.movieapp.core.data.source.remote.network.ApiResponse
import com.cendekia.movieapp.core.data.source.remote.response.WatchesResponse
import com.cendekia.movieapp.core.domain.model.Watches
import com.cendekia.movieapp.core.domain.repository.IWatchesRepository
import com.cendekia.movieapp.core.utils.AppExecutors
import com.cendekia.movieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WatchesRepository(

    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

) : IWatchesRepository {

    override fun getAllWatches(): Flow<Resource<List<Watches>>> =
        object : NetworkBoundResource<List<Watches>, List<WatchesResponse>>() {
            override fun loadFromDB(): Flow<List<Watches>> {
                return localDataSource.getAllWatches().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Watches>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<WatchesResponse>>> =
                remoteDataSource.getAllWatches()

            override suspend fun saveCallResult(data: List<WatchesResponse>) {
                val watchesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertWatches(watchesList)
            }
        }.asFlow()

    override fun getFavoriteWatches(): Flow<List<Watches>> {
        return localDataSource.getFavoriteWatches().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteWatches(watches: Watches, state: Boolean) {

        val watchesEntities = DataMapper.mapDomainToEntity(watches)
        appExecutors.diskIO().execute { localDataSource.setFavoriteWatches(watchesEntities, state) }
    }
}

