package com.org.data.data_source.remote

import com.org.data.data_source.ApiEndpoints
import com.org.data.dto.FilmsListDTO
import retrofit2.http.GET

interface FilmsRemoteDataSource {

    @GET(ApiEndpoints.FILMS)
    suspend fun getFilmsList(): FilmsListDTO

}