package ru.itis.core.domain.usecase

import dagger.Reusable
import ru.itis.core.domain.models.User
import ru.itis.core.domain.repository.DatabaseRepository
import javax.inject.Inject

/**
 * Created by Iskandar on 10.04.2022.
 */

interface IDatabaseUseCase {
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun getUsers(): List<User>
    suspend fun getCurrentUserId(): String?
}

@Reusable
class DatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : IDatabaseUseCase {

    override suspend fun addUser(user: User) {
        databaseRepository.addUser(user)
    }

    override suspend fun updateUser(user: User) {
        databaseRepository.updateUser(user)
    }

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUserId(): String? {
        return databaseRepository.getCurrentUserId()
    }
}
