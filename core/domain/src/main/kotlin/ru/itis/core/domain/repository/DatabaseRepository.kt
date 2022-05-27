package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.models.User
import ru.itis.core.domain.viewstates.ResultState

/**
 * Created by Iskandar on 10.04.2022.
 */

interface DatabaseRepository {
    suspend fun addUser(user: User)
    suspend fun getUsers(): List<User>
    suspend fun updateUser(user: User)
    suspend fun getCurrentUserId(): String?
    suspend fun fetchCurrentUser()

    val userFlowProcess: Flow<ResultState<User, Any>>
}
