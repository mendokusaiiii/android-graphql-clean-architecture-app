package br.com.mendo.myanimelist.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoritesScreen(viewModel: AnimeViewModel = hiltViewModel()) {
    val favorites = viewModel.favoriteAnimes.value
    LazyColumn {
        items(favorites) { anime ->
            AnimeItem(anime = anime, onClick = {})
        }
    }
}