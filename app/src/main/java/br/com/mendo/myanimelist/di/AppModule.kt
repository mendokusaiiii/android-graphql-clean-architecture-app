package br.com.mendo.myanimelist.di

import android.content.Context
import androidx.room.Room
import br.com.mendo.myanimelist.data.local.AnimeDao
import br.com.mendo.myanimelist.data.local.AnimeDatabase
import br.com.mendo.myanimelist.data.remote.JikanApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesJikanApi(): JikanApi {
         return Retrofit.Builder()
             .baseUrl("https://api.jikan.moe/v4/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(JikanApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(@ApplicationContext context: Context): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(database: AnimeDatabase): AnimeDao {
        return database.animeDao()
    }
}