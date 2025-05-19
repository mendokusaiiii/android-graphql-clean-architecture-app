package br.com.mendo.myanimelist.domain.repository

import br.com.mendo.myanimelist.data.local.AnimeDao
import br.com.mendo.myanimelist.data.local.AnimeEntity
import br.com.mendo.myanimelist.data.model.Anime
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dao: AnimeDao
): AnimeRepository {
    override suspend fun getPopularAnimes(): List<Anime> {
        val response = apolloClient.query(GetPopularAnimesQuery(page = 1, perPage = 10)).execute()
        return response.data?.Page?.media?.mapNotNull { media ->
            Anime(
                id = media?.id ?: return@mapNotNull null,
                title = media.title?.romaji ?: "",
                synopsis = media.description ?: "",
                imageUrl = media.coverImage?.large ?: ""
            )
        } ?: emptyList()
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