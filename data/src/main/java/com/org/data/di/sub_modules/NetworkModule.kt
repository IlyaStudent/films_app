package com.org.data.di.sub_modules

import com.org.data.di.config.NetworkConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    // LoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // OkHttpClient
    single {
        val loggingInterceptor = get<HttpLoggingInterceptor>()
        val networkConfig = get<NetworkConfig>()

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(
                networkConfig.connectTimeout,
                TimeUnit.SECONDS,
            )
            .readTimeout(
                networkConfig.readTimeout,
                TimeUnit.SECONDS
            )
            .build()
    }

    // Json
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    // Converter
    single {
        val json = get<Json>()
        json.asConverterFactory(
            "application/json".toMediaType()
        )
    }

    // Retrofit
    single {
        val networkConfig = get<NetworkConfig>()
        val converterFactory = get<Converter.Factory>()
        val client = get<OkHttpClient>()

        Retrofit.Builder()
            .baseUrl(networkConfig.baseUrl)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }
}