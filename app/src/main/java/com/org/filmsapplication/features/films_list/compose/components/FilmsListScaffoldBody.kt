package com.org.filmsapplication.features.films_list.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.org.domain.entity.FilmEntity
import com.org.filmsapplication.R
import com.org.filmsapplication.core.ui.theme.Typography
import com.org.filmsapplication.features.films_list.utils.Genre

@Composable
fun FilmsListScaffoldBody(
    modifier: Modifier = Modifier,
    films: List<FilmEntity>,
    onGenreFilterClick: (Genre) -> Unit,
    selectedGenre: Genre?,
    onFilmClick: (FilmEntity) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        item(
            span = { GridItemSpan(2) }
        ) {
            FilmsGenreFilterComponent(
                modifier = Modifier.padding(bottom = 8.dp),
                onGenreFilterClick = onGenreFilterClick,
                selectedGenre = selectedGenre,
            )
        }

        item(
            span = { GridItemSpan(2) },
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.films),
                style = Typography.titleMedium
            )
        }

        items(
            items = films,
            key = { it.id }
        ) { film ->
            FilmCard(
                modifier = Modifier.padding(
                    start = if (films.indexOf(film) % 2 == 0) 16.dp else 0.dp,
                    end = if (films.indexOf(film) % 2 == 1) 16.dp else 0.dp,
                ),
                film = film,
                onFilmClick = { onFilmClick(film) }
            )
        }
    }

}