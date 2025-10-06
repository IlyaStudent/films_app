package com.org.filmsapplication.features.films_list.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.org.filmsapplication.R
import com.org.filmsapplication.core.ui.compose.CustomProgressIndicator
import com.org.filmsapplication.core.ui.compose.CustomTopAppBar
import com.org.filmsapplication.core.ui.compose.ErrorSnackBar
import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.FilmsCatalogCommand
import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.FilmsCatalogViewModel
import com.org.filmsapplication.core.utils.StateContent
import com.org.filmsapplication.features.films_list.compose.components.FilmsListScaffoldBody
import kotlinx.coroutines.launch

@Composable
fun FilmListScreen(
    viewModel: FilmsCatalogViewModel,
    navigateToFilmInfo: (Int) -> Unit,
) {
    val responseState by viewModel.responseState.collectAsState()
    val filteredFilms by viewModel.filteredFilms.collectAsState()
    val selectedGenre by viewModel.selectedGenreState.collectAsState()

    val loadDataCallback = { viewModel.processCommand(FilmsCatalogCommand.GetAllFilms) }

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val networkErrorMessage = stringResource(R.string.network_error)
    val repeatLabel = stringResource(R.string.repeat)

    LaunchedEffect(Unit) {
        if (responseState.isInitial) {
            loadDataCallback()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = stringResource(R.string.films),
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { data ->
                ErrorSnackBar(
                    data
                )
            }
        },
        containerColor = Color.White
    ) { paddingValues ->

        responseState.StateContent(
            onInitial = {

            },
            onLoading = {
                CustomProgressIndicator()
            },
            onSuccess = {
                FilmsListScaffoldBody(
                    modifier = Modifier.padding(paddingValues),
                    films = filteredFilms,
                    selectedGenre = selectedGenre,
                    onGenreFilterClick = { genre ->
                        viewModel.processCommand(
                            FilmsCatalogCommand.SelectGenreFilter(genre)
                        )
                    },
                    onFilmClick = { film ->
                        viewModel.processCommand(
                            FilmsCatalogCommand.SelectFilm(film.id)
                        )
                        navigateToFilmInfo(film.id)
                    }
                )
            },
            onError = { error ->
                LaunchedEffect(error) {
                    scope.launch {

                        val result = snackBarHostState.showSnackbar(
                            networkErrorMessage,
                            repeatLabel,
                            duration = SnackbarDuration.Indefinite
                        )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                viewModel.processCommand(FilmsCatalogCommand.GetAllFilms)
                            }

                            SnackbarResult.Dismissed -> {
                            }
                        }
                    }

                }
            }
        )

    }
}
