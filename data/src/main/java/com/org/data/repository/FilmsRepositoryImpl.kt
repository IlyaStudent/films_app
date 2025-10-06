package com.org.data.repository

import com.org.data.data_source.remote.FilmsRemoteDataSource
import com.org.data.mapper.toEntity
import com.org.domain.entity.FilmsListEntity
import com.org.domain.repository.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FilmsRepositoryImpl(
    private val filmsRemoteDataSource: FilmsRemoteDataSource,
) : FilmsRepository {
    override fun getFilmsList(): Flow<FilmsListEntity> {
        return flow {
            val data = filmsRemoteDataSource.getFilmsList()
            emit(data.toEntity())
        }.flowOn(Dispatchers.IO)
    }
}