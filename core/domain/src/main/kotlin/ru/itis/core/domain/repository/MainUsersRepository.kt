package ru.itis.core.domain.repository

import ru.itis.core.domain.models.User

/**
 * Created by Iskandar on 02.06.2022.
 */

interface MainUsersRepository {
    suspend fun getAllUsersByInterests(): List<User>
}
