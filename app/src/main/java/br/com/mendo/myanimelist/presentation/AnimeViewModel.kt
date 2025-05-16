package br.com.mendo.myanimelist.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mendo.myanimelist.data.model.Anime
import br.com.mendo.myanimelist.domain.usecase.AddFavoriteAnimeUseCase
import br.com.mendo.myanimelist.domain.usecase.GetFavoriteAnimesUseCase
import br.com.mendo.myanimelist.domain.usecase.GetPopularAnimesUseCase
import br.com.mendo.myanimelist.domain.usecase.RemoveFavoriteAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getPopularAnimesUseCase: GetPopularAnimesUseCase,
    private val getFavoriteAnimesUseCase: GetFavoriteAnimesUseCase,
    private val addFavoriteAnimeUseCase: AddFavoriteAnimeUseCase,
    private val removeFavoriteAnimeUseCase: RemoveFavoriteAnimeUseCase
): ViewModel() {
    private val _popularAnimes = mutableStateOf<List<Anime>>(emptyList())
    val popularAnimes: State<List<Anime>> = _popularAnimes

    private val _favoriteAnimes = mutableStateOf<List<Anime>>(emptyList())
    val favoriteAnimes: State<List<Anime>> = _favoriteAnimes

    init {
        viewModelScope.launch {
            _popularAnimes.value = getPopularAnimesUseCase()
            _favoriteAnimes.value = getFavoriteAnimesUseCase()
        }
    }

    fun addFavorite(anime: Anime) {
        viewModelScope.launch {
            addFavoriteAnimeUseCase(anime)
            _favoriteAnimes.value = getFavoriteAnimesUseCase()
            Log.d("Analytics", "Event: favorite_added, anime_id: ${anime.malId}")
        }
    }

    fun removeFavorite(anime: Anime) {
        viewModelScope.launch {
            removeFavoriteAnimeUseCase(anime)
            _favoriteAnimes.value = getFavoriteAnimesUseCase()
        }
    }
}