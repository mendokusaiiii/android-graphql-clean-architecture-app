package br.com.mendo.myanimelist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val malId: Int,
    val title: String,
    val synopsis: String,
    val imageUrl: String,
): Parcelable
