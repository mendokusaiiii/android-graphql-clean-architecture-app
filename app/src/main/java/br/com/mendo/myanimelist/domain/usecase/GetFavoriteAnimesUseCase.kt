package br.com.mendo.myanimelist.domain.usecase

import br.com.mendo.myanimelist.domain.repository.AnimeRepository
import javax.inject.Inject

class GetFavoriteAnimesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke() = repository.getFavoriteAnimes()
}