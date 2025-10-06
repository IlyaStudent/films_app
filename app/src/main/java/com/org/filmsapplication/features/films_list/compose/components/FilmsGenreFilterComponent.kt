package com.org.filmsapplication.features.films_list.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.org.filmsapplication.R
import com.org.filmsapplication.core.ui.theme.Typography
import com.org.filmsapplication.core.ui.theme.Yellow
import com.org.filmsapplication.features.films_list.utils.Genre

@Composable
fun FilmsGenreFilterComponent(
    modifier: Modifier = Modifier,
    onGenreFilterClick: (Genre) -> Unit,
    selectedGenre: Genre?,
) {

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
            text = stringResource(R.string.genres),
            style = Typography.titleMedium
        )

        Genre.entries.forEach {
            GenreFilterChip(
                genre = it,
                selectedGenre = selectedGenre,
                onGenreFilterClick = onGenreFilterClick
            )
        }
    }
}

@Composable
private fun GenreFilterChip(
    modifier: Modifier = Modifier,
    genre: Genre,
    onGenreFilterClick: (Genre) -> Unit,
    selectedGenre: Genre?,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(if (genre == selectedGenre) Yellow else Color.White)
            .clickable(
                onClick = { onGenreFilterClick(genre) }
            )
            .padding(
                vertical = 10.dp,
                horizontal = 16.dp
            )
    ) {
        Text(
            text = stringResource(genre.stringResource)
                .replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase()
                    else it.toString()
                },
            style = Typography.bodyLarge
        )
    }
}