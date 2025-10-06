package com.org.filmsapplication.core.ui.view_model.film_catalog_view_model

import com.org.filmsapplication.features.films_list.utils.Genre

sealed interface FilmsCatalogCommand {

    data object GetAllFilms : FilmsCatalogCommand

    data class SelectGenreFilter(
        val genre: Genre,
    ) : FilmsCatalogCommand

    data class SelectFilm(
        val filmId: Int,
    ) : FilmsCatalogCommand

}