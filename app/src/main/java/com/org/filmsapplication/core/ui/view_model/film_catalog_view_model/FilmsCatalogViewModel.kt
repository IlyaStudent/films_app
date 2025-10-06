package com.org.filmsapplication.core.ui.view_model.film_catalog_view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.domain.entity.FilmEntity
import com.org.domain.entity.FilmsListEntity
import com.org.domain.use_case.GetFilmsListUseCase
import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.managers.FilmSelectionController
import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.managers.FilmSelectionManager
import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.managers.FilmsFilterController
import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.managers.FilmsFilterManager
import com.org.filmsapplication.core.utils.ApiResponseState
import com.org.filmsapplication.core.utils.extensions.filterByGenre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

typealias FilmsListState = ApiResponseState<FilmsListEntity>

// Не уверен, что это лучший подход по разделению зон отвественности вью модели,
// но хотелось немного поэксперементировать
class FilmsCatalogViewModel(
    private val getFilmsListUseCase: GetFilmsListUseCase,
) : ViewModel(),
    FilmsFilterController by FilmsFilterManager(),
    FilmSelectionController by FilmSelectionManager() {

    private val _responseState = MutableStateFlow<FilmsListState>(ApiResponseState.Initial)
    val responseState = _responseState.asStateFlow()

    val filteredFilms: StateFlow<List<FilmEntity>> =
        _responseState.combine(selectedGenreState) { response, genre ->
            when (response) {
                is ApiResponseState.Success ->
                    response.data.films.filterByGenre(genre)

                else -> emptyList()
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun processCommand(command: FilmsCatalogCommand) {
        when (command) {
            FilmsCatalogCommand.GetAllFilms -> {
                getFilms()
            }

            is FilmsCatalogCommand.SelectGenreFilter -> {
                toggleGenre(command.genre)
            }

            is FilmsCatalogCommand.SelectFilm -> {
                val film = filteredFilms.value.find { it.id == command.filmId }
                selectFilm(film)
            }
        }
    }

    private fun getFilms() {
        getFilmsListUseCase()
            .onStart {
                _responseState.value = ApiResponseState.Loading()
            }
            .onEach { data ->
                val sortedFilms = data.films.sortedBy { it.localizedName.lowercase() }
                val sortedData = data.copy(films = sortedFilms)
                _responseState.value = ApiResponseState.Success(sortedData)
            }
            .catch { throwable ->
                _responseState.value = ApiResponseState.Error(throwable)
            }
            .launchIn(viewModelScope)
    }


}