package ru.itis.core.domain.usecase

import ru.itis.core.domain.repository.ISignInRepository
import javax.inject.Inject

/**
 * Copyright (c) 09.03.2022 Created by Iskandar
 */


interface ISignInUseCase {
    suspend fun logout()
}

internal class SignInUseCase @Inject constructor(
    private val iSignInRepository: ISignInRepository
):ISignInUseCase{

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}
