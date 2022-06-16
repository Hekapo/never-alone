package ru.itis.core.domain.usecase

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.models.User
import ru.itis.core.domain.repository.SignInRepository
import ru.itis.core.domain.viewstates.ResultState
import javax.inject.Inject

/**
 * Copyright (c) 09.03.2022 Created by Iskandar
 */

interface ISignInUseCase {
    val signInState: Flow<ResultState<String, String>>
    val signInWithGoogleState: Flow<ResultState<User, String>>
    suspend fun trySignIn(email: String, password: String)
    suspend fun signInWithGoogle(token: String)
    suspend fun getCurrentUser(): User
    suspend fun logout()
}

@Reusable
internal class SignInUseCase @Inject constructor(
    private val iSignInRepository: SignInRepository
) : ISignInUseCase {

    override val signInState: Flow<ResultState<String, String>>
        get() = iSignInRepository.signInProcessState.distinctUntilChanged()

    override val signInWithGoogleState: Flow<ResultState<User, String>>
        get() = iSignInRepository.signInWithGoogleProcessState.distinctUntilChanged()

    override suspend fun trySignIn(email: String, password: String) {
        iSignInRepository.trySignInWithEmailAndPassword(email, password)
    }

    override suspend fun signInWithGoogle(token: String) {
        iSignInRepository.signInWithGoogle(token)
    }

    override suspend fun getCurrentUser(): User {
        return iSignInRepository.getSignedUser()
    }

    override suspend fun logout() {
        iSignInRepository.logout()
    }
}

