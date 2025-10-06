package com.org.filmsapplication.features.films_list.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.org.domain.entity.FilmEntity
import com.org.filmsapplication.R
import com.org.filmsapplication.core.ui.compose.CustomAsyncImage
import com.org.filmsapplication.core.ui.theme.Typography

@Composable
fun FilmCard(
    modifier: Modifier = Modifier,
    film: FilmEntity,
    onFilmClick: () -> Unit,
) {
    Column(
        modifier = modifier.clickable(
            onClick = {
                onFilmClick()
            }
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f/3f)
                .clip(RoundedCornerShape(4.dp))
        ) {
            CustomAsyncImage(
                modifier = Modifier.fillMaxSize(),
                imageUrl = film.imageUrl,
                contentDescription = stringResource(
                    R.string.image_for_film, film.name
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = film.localizedName,
            style = Typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }

}
