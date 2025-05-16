package br.com.mendo.myanimelist.domain.repository

import br.com.mendo.myanimelist.data.local.AnimeDao
import br.com.mendo.myanimelist.data.local.AnimeEntity
import br.com.mendo.myanimelist.data.model.Anime
import br.com.mendo.myanimelist.data.remote.JikanApi
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: JikanApi,
    private val dao: AnimeDao
): AnimeRepository {
    override suspend fun getPopularAnimes(): List<Anime> {
        return api.getTopAnimes().top.map { it.toDomain() }
    }

    override suspend fun getFavoriteAnimes(): List<Anime> {
        return dao.getFavoriteAnimes().map {
            Anime(
                malId = it.malId,
                title = it.title,
                synopsis = it.synopsis,
                imageUrl = it.imageUrl
            )
        }
    }

    override suspend fun addAnimeToFavorites(anime: Anime) {
        dao.insertAnime(AnimeEntity(
            malId = anime.malId,
            title = anime.title,
            synopsis = anime.synopsis,
            imageUrl = anime.imageUrl
        ))
    }

    override suspend fun removeAnimeFromFavorites(anime: Anime) {
        dao.deleteAnime(AnimeEntity(
            malId = anime.malId,
            title = anime.title,
            synopsis = anime.synopsis,
            imageUrl = anime.imageUrl
        ))
    }
}