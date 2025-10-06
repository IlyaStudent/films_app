package com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.managers

import com.org.filmsapplication.features.films_list.utils.Genre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface FilmsFilterController {
    val selectedGenreState: StateFlow<Genre?>
    fun toggleGenre(genre: Genre)
}

class FilmsFilterManager : FilmsFilterController {
    private val _selectedGenreState = MutableStateFlow<Genre?>(null)
    override val selectedGenreState = _selectedGenreState.asStateFlow()

    override fun toggleGenre(genre: Genre) {
        _selectedGenreState.value =
            if (_selectedGenreState.value == genre) null
            else genre
    }
}