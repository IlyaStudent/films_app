package com.org.domain.use_case

import com.org.domain.entity.FilmsListEntity
import com.org.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.Flow

class GetFilmsListUseCase(
    private val filmsRepository: FilmsRepository,
) {
    operator fun invoke(): Flow<FilmsListEntity> {
        return filmsRepository.getFilmsList()
    }
}