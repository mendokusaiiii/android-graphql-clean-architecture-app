package br.com.mendo.myanimelist.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.mendo.myanimelist.data.model.Anime
import coil.compose.AsyncImage

@Preview
@Composable
fun PopularAnimesScreenPreview() {
    PopularAnimesScreen(onAnimeClick = {})
}

@Composable
fun PopularAnimesScreen(
    viewModel: AnimeViewModel = hiltViewModel(),
    onAnimeClick: (Anime) -> Unit
) {
    val animes = viewModel.popularAnimes.value
    LazyColumn {
        items(animes) { anime ->
            AnimeItem(anime = anime, onClick = { onAnimeClick(anime) })
        }
    }
}

@Composable
fun AnimeItem(anime: Anime, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Row {
            AsyncImage(
                model = anime.imageUrl,
                contentDescription = anime.title,
                modifier = Modifier.size(100.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(anime.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    anime.synopsis,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}