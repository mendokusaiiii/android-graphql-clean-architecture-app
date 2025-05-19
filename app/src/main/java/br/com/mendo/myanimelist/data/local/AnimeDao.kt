package br.com.mendo.myanimelist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime WHERE malId IN (:ids)")
    suspend fun getFavoriteAnimes(ids: List<Int>): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)

    @Delete
    suspend fun deleteAnime(anime: AnimeEntity)

    @Query("SELECT * FROM anime WHERE cachedAt > :minCachedAt")
    suspend fun getCachedAnimes(minCachedAt: Long): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animes: List<AnimeEntity>)

    @Query("DELETE FROM anime WHERE cachedAt > 0")
    suspend fun clearCachedAnimes()
}