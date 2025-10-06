package com.org.filmsapplication.core.di

import com.org.data.di.config.NetworkConfig
import com.org.filmsapplication.BuildConfig
import com.org.filmsapplication.core.di.config.NetworkConfigImpl
import org.koin.dsl.module

val appModule = module {

    single<NetworkConfig> {
        NetworkConfigImpl(
            baseUrl = BuildConfig.API_URL
        )
    }

}