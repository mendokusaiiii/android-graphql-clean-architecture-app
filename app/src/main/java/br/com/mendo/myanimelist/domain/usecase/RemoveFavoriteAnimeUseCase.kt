package br.com.mendo.myanimelist.domain.usecase

import br.com.mendo.myanimelist.domain.repository.AnimeRepository
import javax.inject.Inject

class RemoveFavoriteAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(animeId: Int) {
        val anime = repository.getFavoriteAnimes().find { it.malId == animeId }
        if (anime != null) {
            repository.removeAnimeFromFavorites(anime)
        }
    }
}