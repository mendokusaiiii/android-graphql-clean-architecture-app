package br.com.mendo.myanimelist.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.mendo.myanimelist.data.model.Anime
import coil.compose.AsyncImage

@Composable
fun AnimeDetailsScreen(
    anime: Anime,
    viewModel: AnimeViewModel = hiltViewModel()
) {
    val favorites = viewModel.favoriteAnimes.value
    val isFavorite = favorites.any { it.malId == anime.malId }

    Column(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = anime.imageUrl,
            contentDescription = anime.title,
            modifier = Modifier.fillMaxWidth()
        )
        Text(anime.title, style = MaterialTheme.typography.headlineSmall)
        Text(anime.synopsis, style = MaterialTheme.typography.bodyMedium)
        Button(
            onClick = {
                if (isFavorite) viewModel.removeFavorite(anime)
                else viewModel.addFavorite(anime)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(if (isFavorite) "Remove from Favorites" else "Add to Favorites")
        }
    }
}