package br.com.mendo.myanimelist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    suspend fun getFavoriteAnimes(): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)

    @Delete
    suspend fun deleteAnime(anime: AnimeEntity)
}