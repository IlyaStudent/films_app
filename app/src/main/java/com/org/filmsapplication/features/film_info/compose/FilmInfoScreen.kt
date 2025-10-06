package com.org.filmsapplication.features.film_info.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.org.filmsapplication.R
import com.org.filmsapplication.core.ui.compose.CustomAsyncImage
import com.org.filmsapplication.core.ui.compose.CustomTopAppBar
import com.org.filmsapplication.core.ui.theme.Blue
import com.org.filmsapplication.core.ui.theme.GreyText
import com.org.filmsapplication.core.ui.theme.Typography
import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.FilmsCatalogViewModel
import com.org.filmsapplication.core.utils.extensions.shorten

@Composable
fun FilmInfoScreen(
    onBackClick: () -> Unit,
    viewModel: FilmsCatalogViewModel,
) {
    val filmState by viewModel.selectedFilmState.collectAsState()

    filmState?.let { film ->
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    title = film.localizedName,
                    onBackClick = onBackClick,
                )
            },
            containerColor = Color.White,
        ) { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        horizontal = 16.dp,
                        vertical = 24.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .clip(
                            RoundedCornerShape(4.dp)
                        )


                ) {
                    CustomAsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        imageUrl = film.imageUrl,
                        contentDescription = stringResource(
                            R.string.image_for_film, film.name
                        )
                    )
                }

                Column {
                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Text(
                        text = film.localizedName,
                        style = Typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "${film.genres.joinToString()}, ${film.year} год ",
                        style = Typography.bodyLarge,
                        color = GreyText
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            text = film.rating?.shorten(1).toString(),
                            style = Typography.titleLarge,
                            fontSize = 24.sp,
                            lineHeight = 28.sp,
                            color = Blue,
                        )

                        Text(
                            text = stringResource(R.string.kinopoisk),
                            style = Typography.headlineMedium,
                            color = Blue,
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Text(
                        text = film.description ?: "",
                        style = Typography.bodyMedium,
                    )
                }
            }

        }
    }
}