package ru.itis.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.repository.DatastoreRepository
import javax.inject.Inject

/**
 * Created by Iskandar on 26.05.2022.
 */

interface IDatastoreUseCase {
    suspend fun saveOnBoardingState(completed: Boolean)
    suspend fun readOnBoardingState(): Flow<Boolean>
}

internal class DatastoreUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : IDatastoreUseCase {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        datastoreRepository.saveOnBoardingState(completed)
    }

    override suspend fun readOnBoardingState(): Flow<Boolean> {
        return datastoreRepository.readOnBoardingState()
    }
}
