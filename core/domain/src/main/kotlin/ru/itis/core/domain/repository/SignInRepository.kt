package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.models.User
import ru.itis.core.domain.viewstates.ResultState

/**
 * Copyright (c) 09.03.2022 Created by Iskandar
 */

interface SignInRepository {
    val signInProcessState: Flow<ResultState<String, String>>
    val signInWithGoogleProcessState: Flow<ResultState<User, String>>
    suspend fun trySignInWithEmailAndPassword(email: String, password: String)
    suspend fun signInWithGoogle(tokenId: String)
    suspend fun getSignedUser(): User
    suspend fun logout()
}
