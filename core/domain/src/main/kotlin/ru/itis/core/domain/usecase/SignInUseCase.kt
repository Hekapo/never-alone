package ru.itis.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.repository.ISignInRepository
import ru.itis.core.domain.viewstates.SignInState
import javax.inject.Inject

/**
 * Copyright (c) 09.03.2022 Created by Iskandar
 */


interface ISignInUseCase {
    val signInState: Flow<SignInState>
    suspend fun trySignIn(email: String, password: String)
    suspend fun logout()
}

internal class SignInUseCase @Inject constructor(
    private val iSignInRepository: ISignInRepository
) : ISignInUseCase {
    override val signInState: Flow<SignInState>
        get() = iSignInRepository.signInProcess.distinctUntilChanged()

    override suspend fun trySignIn(email: String, password: String) {
        iSignInRepository.trySignInWithEmailAndPassword(email, password)
    }

    override suspend fun logout() {
        iSignInRepository.logout()
    }
}

