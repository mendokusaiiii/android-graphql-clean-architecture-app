package br.com.mendo.myanimelist.domain.repository

import br.com.mendo.myanimelist.data.local.AnimeDao
import br.com.mendo.myanimelist.data.local.AnimeEntity
import br.com.mendo.myanimelist.data.model.Anime
import com.apollographql.apollo3.ApolloClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val dao: AnimeDao
): AnimeRepository {
    companion object {
        private const val CACHE_DURATION_MS = 24 * 60 * 60 * 1000
    }

    override suspend fun getPopularAnimes(): List<Anime> = withContext(Dispatchers.IO) {
        val minCachedAt = System.currentTimeMillis() - CACHE_DURATION_MS
        val cachedAnimes = dao.getCachedAnimes(minCachedAt)
        if (cachedAnimes.isNotEmpty()) {
            return@withContext cachedAnimes.map {
                Anime(it.malId, it.title, it.synopsis, it.imageUrl)
            }
        }

        val response = apolloClient.query(GetPopularAnimesQuery(page = 1, perPage = 10)).execute()
        val animes = response.data?.Page?.media?.mapNotNull { media ->
            Anime(
                id = media?.malId ?: return@mapNotNull null,
                title = media.title?.romaji ?: "",
                synopsis = media.description ?: "",
                imageUrl = media.coverImage?.large ?: ""
            )
        } ?: emptyList()

        dao.clearCachedAnimes()
        dao.insertAll(animes.map {
            AnimeEntity(
                malId = it.malId,
                title = it.title,
                synopsis = it.synopsis,
                imageUrl = it.imageUrl,
                cachedAt = System.currentTimeMillis()
            )
        })

        animes
    }

    override suspend fun getFavoriteAnimes(): List<Anime> {
        return dao.getFavoriteAnimes(emptyList()).map {
            Anime(it.malId, it.title, it.synopsis, it.imageUrl)
        }
    }

    override suspend fun addAnimeToFavorites(anime: Anime) {
        dao.insertAnime(AnimeEntity(anime.malId, anime.title, anime.synopsis, anime.imageUrl))
    }

    override suspend fun removeAnimeFromFavorites(anime: Anime) {
        dao.deleteAnime(AnimeEntity(anime.malId, anime.title, anime.synopsis, anime.imageUrl))
    }
}