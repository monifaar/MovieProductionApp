package com.cendekia.movieapp.core.di

import androidx.room.Room
import com.cendekia.movieapp.core.data.WatchesRepository
import com.cendekia.movieapp.core.data.source.local.LocalDataSource
import com.cendekia.movieapp.core.data.source.local.room.WatchesDatabase
import com.cendekia.movieapp.core.data.source.remote.RemoteDataSource
import com.cendekia.movieapp.core.data.source.remote.network.ApiService
import com.cendekia.movieapp.core.domain.repository.IWatchesRepository
import com.cendekia.movieapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("cendekia".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            WatchesDatabase::class.java, "Movie.db"

        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
                .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
                .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
                .build()
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
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