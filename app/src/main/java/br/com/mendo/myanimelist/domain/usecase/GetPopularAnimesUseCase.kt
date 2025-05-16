package br.com.mendo.myanimelist.domain.usecase

import br.com.mendo.myanimelist.domain.repository.AnimeRepository
import javax.inject.Inject

class GetPopularAnimesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke() = repository.getPopularAnimes()
}