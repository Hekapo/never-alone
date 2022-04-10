package ru.itis.core.domain.repository

import ru.itis.core.domain.models.User

/**
 * Created by Iskandar on 10.04.2022.
 */

interface DatabaseRepository {
    suspend fun addUser(user: User)
    suspend fun getUsers(): List<User>
    suspend fun updateUser(user: User)
    suspend fun getCurrentUserId(): String?
}
