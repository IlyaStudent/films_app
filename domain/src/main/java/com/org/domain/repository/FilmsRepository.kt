package com.org.domain.repository

import com.org.domain.entity.FilmsListEntity
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    fun getFilmsList(): Flow<FilmsListEntity>
}