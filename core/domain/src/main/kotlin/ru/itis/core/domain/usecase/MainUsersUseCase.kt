package ru.itis.core.domain.usecase

import dagger.Reusable
import ru.itis.core.domain.models.User
import ru.itis.core.domain.repository.MainUsersRepository
import javax.inject.Inject

/**
 * Created by Iskandar on 02.06.2022.
 */

interface IMainUsersUseCase {

    suspend fun getMatchedUsers(): List<User>
}

@Reusable
class MainUsersUseCase @Inject constructor(
    private val repository: MainUsersRepository
) : IMainUsersUseCase {
    override suspend fun getMatchedUsers(): List<User> {
        return repository.getAllUsersByInterests()
    }
}
