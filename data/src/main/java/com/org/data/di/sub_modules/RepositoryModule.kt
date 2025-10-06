package com.org.data.di.sub_modules

import com.org.data.data_source.remote.FilmsRemoteDataSource
import com.org.data.repository.FilmsRepositoryImpl
import com.org.domain.repository.FilmsRepository
import org.koin.dsl.module

val repositoryModule = module {

    // FilmsRepository
    single<FilmsRepository> {
        FilmsRepositoryImpl(
            get<FilmsRemoteDataSource>()
        )
    }

}