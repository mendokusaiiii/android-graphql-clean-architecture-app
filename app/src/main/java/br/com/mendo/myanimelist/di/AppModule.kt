package br.com.mendo.myanimelist.di

import android.content.Context
import androidx.room.Room
import br.com.mendo.myanimelist.data.local.AnimeDao
import br.com.mendo.myanimelist.data.local.AnimeDatabase
import br.com.mendo.myanimelist.data.remote.JikanApi
import com.apollographql.apollo3.ApolloClient
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
    fun provideApolloClient(): ApolloClient {
         return ApolloClient.Builder().serverUrl("https://graphql.anilist.co").build()
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(@ApplicationContext context: Context): AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_db"
        ).addMigrations(AnimeDatabase.MIGRATION_1_2).build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(database: AnimeDatabase): AnimeDao {
        return database.animeDao()
    }
}