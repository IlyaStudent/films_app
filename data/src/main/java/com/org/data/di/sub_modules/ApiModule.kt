package com.org.data.di.sub_modules

import com.org.data.data_source.remote.FilmsRemoteDataSource
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val apiModule = module {

    // FilmsRemoteDataSource
    single {
        get<Retrofit>().create<FilmsRemoteDataSource>()
    }

}