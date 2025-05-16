package br.com.mendo.myanimelist.data.remote

import br.com.mendo.myanimelist.data.model.Anime
import retrofit2.http.GET

interface JikanApi {
    @GET("top/anime")
    suspend fun getTopAnimes(): AnimeResponse
}

data class AnimeResponse(
    val top: List<AnimeDto>
)

data class AnimeDto(
    val malId: Int,
    val title: String,
    val synopsis: String,
    val images: ImageDto,
) {
    fun toDomain() = Anime (
        malId = malId,
        title = title,
        synopsis = synopsis,
        imageUrl = images.jpg.imageUrl
    )
}

data class ImageDto(
    val jpg: JpgDto
)

data class JpgDto (
    val imageUrl: String
)