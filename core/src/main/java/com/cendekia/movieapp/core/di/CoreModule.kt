package com.cendekia.movieapp.core.di

import androidx.room.Room
import com.cendekia.movieapp.core.data.WatchesRepository
import com.cendekia.movieapp.core.data.source.local.LocalDataSource
import com.cendekia.movieapp.core.data.source.local.room.WatchesDatabase
import com.cendekia.movieapp.core.data.source.remote.RemoteDataSource
import com.cendekia.movieapp.core.data.source.remote.network.ApiService
import com.cendekia.movieapp.core.domain.repository.IWatchesRepository
import com.cendekia.movieapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<WatchesDatabase>().watchesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            WatchesDatabase::class.java, "Watches.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {

        val retrofits = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofits.create(ApiService::class.java)

    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IWatchesRepository> {
        WatchesRepository(
            get(),
            get(),
            get()

        )
    }
}