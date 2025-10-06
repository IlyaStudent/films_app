package com.org.filmsapplication.core.di

import com.org.domain.use_case.GetFilmsListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetFilmsListUseCase)
}
