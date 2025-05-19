package br.com.mendo.myanimelist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val malId: Int,
    val title: String,
    val synopsis: String,
    val imageUrl: String,
    val cachedAt: Long = System.currentTimeMillis()
)
