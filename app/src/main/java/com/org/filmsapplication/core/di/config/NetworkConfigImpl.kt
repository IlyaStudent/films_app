package com.org.filmsapplication.core.di.config

import com.org.data.di.config.NetworkConfig

class NetworkConfigImpl(
    override val baseUrl: String,
    override val connectTimeout: Long = 30L,
    override val readTimeout: Long = 30L
) : NetworkConfig