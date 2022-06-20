package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by Iskandar on 26.05.2022.
 */

interface DatastoreRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    val readOnBoardingState: Flow<Boolean>
}
