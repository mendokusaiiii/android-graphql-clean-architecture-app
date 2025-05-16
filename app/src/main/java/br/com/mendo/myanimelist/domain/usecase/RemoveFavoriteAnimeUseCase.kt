package br.com.mendo.myanimelist.domain.usecase

import br.com.mendo.myanimelist.data.model.Anime
import br.com.mendo.myanimelist.domain.repository.AnimeRepository
import javax.inject.Inject

class RemoveFavoriteAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(animeId: Anime) {
        repository.removeAnimeFromFavorites(animeId)
    }
}