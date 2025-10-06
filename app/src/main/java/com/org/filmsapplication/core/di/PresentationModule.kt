package com.org.filmsapplication.core.di

import com.org.filmsapplication.core.ui.view_model.film_catalog_view_model.FilmsCatalogViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { FilmsCatalogViewModel(get()) }
}