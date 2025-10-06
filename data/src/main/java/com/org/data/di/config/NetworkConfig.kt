package com.org.data.di.config

interface NetworkConfig {
    val baseUrl: String
    val connectTimeout: Long
    val readTimeout: Long
}