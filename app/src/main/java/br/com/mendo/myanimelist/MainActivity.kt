package br.com.mendo.myanimelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.mendo.myanimelist.data.model.Anime
import br.com.mendo.myanimelist.presentation.AnimeDetailsScreen
import br.com.mendo.myanimelist.presentation.FavoritesScreen
import br.com.mendo.myanimelist.presentation.PopularAnimesScreen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AnimeAppNavigation()
            }
        }
    }
}

@Composable
fun AnimeAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "popular") {
        composable("popular") {
            PopularAnimesScreen { anime ->
                navController.navigate("details/${anime.malId}")
            }
        }
        composable("favorites") {
            FavoritesScreen()
        }
        composable("details/{animeId}") { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull()
            val anime = navController.previousBackStackEntry?.savedStateHandle?.get<Anime>("anime")
            anime?.let { AnimeDetailsScreen(it) }
        }
    }
}