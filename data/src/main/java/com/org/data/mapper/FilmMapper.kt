package com.org.data.mapper

import com.org.data.dto.FilmDTO
import com.org.data.dto.FilmsListDTO
import com.org.domain.entity.FilmEntity
import com.org.domain.entity.FilmsListEntity

fun FilmsListDTO.toEntity(): FilmsListEntity {
    return FilmsListEntity(
        films = films.map { it.toEntity() }
    )
}

fun FilmDTO.toEntity(): FilmEntity {
    return FilmEntity(
        id = id,
        localizedName = localizedName,
        name = name,
        year = year,
        rating = rating,
        imageUrl = imageUrl,
        description = description,
        genres = genres
    )
}