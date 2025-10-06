package com.org.filmsapplication.core.utils.extensions

import com.org.domain.entity.FilmEntity
import com.org.filmsapplication.features.films_list.utils.Genre

fun List<FilmEntity>.filterByGenre(genre: Genre?) : List<FilmEntity> {
    return genre?.let { selectedGenre ->
        filter { film ->
            film.genres.any { filmGenre ->
                selectedGenre.russianName.equals(filmGenre, ignoreCase = true)
            }
        }
    } ?: this
}