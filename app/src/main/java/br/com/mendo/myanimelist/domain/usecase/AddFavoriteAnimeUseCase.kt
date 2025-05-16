package br.com.mendo.myanimelist.domain.usecase

import br.com.mendo.myanimelist.domain.repository.AnimeRepository
import javax.inject.Inject

class AddFavoriteAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(animeId: Int) {
        val anime = repository.getPopularAnimes().find { it.malId == animeId }
        if (anime != null) {
            repository.addAnimeToFavorites(anime)
        }
    }
}