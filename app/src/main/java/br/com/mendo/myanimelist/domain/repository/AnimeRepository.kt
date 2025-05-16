package br.com.mendo.myanimelist.domain.repository

import br.com.mendo.myanimelist.data.model.Anime

interface AnimeRepository {
    suspend fun getPopularAnimes(): List<Anime>
    suspend fun getFavoriteAnimes(): List<Anime>
    suspend fun addAnimeToFavorites(anime: Anime)
    suspend fun removeAnimeFromFavorites(anime: Anime)
}