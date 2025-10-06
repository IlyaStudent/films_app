package com.org.filmsapplication.core.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.org.filmsapplication.R
import com.org.filmsapplication.core.ui.theme.GreyBg
import com.org.filmsapplication.core.ui.theme.GreyIcon

@Composable
fun CustomAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    contentDescription: String,
) {
    var hasLoadError by remember { mutableStateOf(false) }

    LaunchedEffect(imageUrl) {
        hasLoadError = false
    }

    if (imageUrl.isNullOrEmpty() || hasLoadError) {
        NoImage(
            modifier = modifier
        )
    } else {
        AsyncImage(
            modifier = modifier,
            model = imageUrl,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            onError = {
                hasLoadError = true
            }
        )
    }
}

@Composable
private fun NoImage(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GreyBg)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(R.drawable.ic_image_directory_svg),
            contentDescription = stringResource(R.string.no_image),
            tint = GreyIcon,
        )
    }
}