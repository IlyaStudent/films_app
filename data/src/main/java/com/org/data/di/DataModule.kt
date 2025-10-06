package com.org.data.di

import com.org.data.di.sub_modules.apiModule
import com.org.data.di.sub_modules.networkModule
import com.org.data.di.sub_modules.repositoryModule

val dataModule = listOf(
    networkModule, apiModule, repositoryModule,
)