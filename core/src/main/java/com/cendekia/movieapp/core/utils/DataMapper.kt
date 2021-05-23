package com.cendekia.movieapp.core.utils

import com.cendekia.movieapp.core.data.source.local.entity.WatchesEntity
import com.cendekia.movieapp.core.data.source.remote.response.WatchesResponse
import com.cendekia.movieapp.core.domain.model.Watches

object DataMapper {
    fun mapResponsesToEntities(input: List<WatchesResponse>): List<WatchesEntity> {

        val watchesList = ArrayList<WatchesEntity>()
        input.map {
            val watches = WatchesEntity(

                movieId = it.id,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                release_data = it.release_date,
                isFavorite = false

            )
            watchesList.add(watches)
        }
        return watchesList
    }

    fun mapEntitiesToDomain(input: List<WatchesEntity>): List<Watches> =
        input.map {
            Watches(

                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                poster_path = it.poster_path,
                release_data = it.release_data,
                isFavorite = it.isFavorite

            )
        }

    fun mapDomainToEntity(input: Watches) = WatchesEntity(

        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        poster_path = input.poster_path,
        release_data = input.release_data,
        isFavorite = input.isFavorite

    )
}