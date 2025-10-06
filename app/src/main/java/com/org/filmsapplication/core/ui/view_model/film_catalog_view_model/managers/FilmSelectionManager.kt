package com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.managers

import com.org.domain.entity.FilmEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface FilmSelectionController {
    val selectedFilmState: StateFlow<FilmEntity?>
    fun selectFilm(film: FilmEntity?)
}

class FilmSelectionManager : FilmSelectionController {

    private val _selectedFilmState = MutableStateFlow<FilmEntity?>(null)
    override val selectedFilmState = _selectedFilmState.asStateFlow()

    override fun selectFilm(film: FilmEntity?) {
        _selectedFilmState.value = film
    }
}