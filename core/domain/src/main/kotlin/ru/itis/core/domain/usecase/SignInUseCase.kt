package ru.itis.core.domain.usecase

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.repository.SignInRepository
import ru.itis.core.domain.viewstates.ResultState
import javax.inject.Inject

/**
 * Copyright (c) 09.03.2022 Created by Iskandar
 */

interface ISignInUseCase {
    val signInState: Flow<ResultState<String, String>>
    suspend fun trySignIn(email: String, password: String)
    suspend fun logout()
}

@Reusable
internal class SignInUseCase @Inject constructor(
    private val iSignInRepository: SignInRepository
) : ISignInUseCase {

    override val signInState: Flow<ResultState<String, String>>
        get() = iSignInRepository.signInProcessState.distinctUntilChanged()

    override suspend fun trySignIn(email: String, password: String) {
        iSignInRepository.trySignInWithEmailAndPassword(email, password)
    }

    override suspend fun logout() {
        iSignInRepository.logout()
    }
}

