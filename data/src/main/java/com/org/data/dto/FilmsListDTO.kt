package com.org.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsListDTO(
    @SerialName("films")
    val films: List<FilmDTO>,
)