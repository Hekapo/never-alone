package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.viewstates.SignInState

/**
 * Copyright (c) 09.03.2022 Created by Iskandar
 */


interface ISignInRepository {
    val signInProcess: Flow<SignInState>
    suspend fun trySignInWithEmailAndPassword(email: String, password: String)
    suspend fun logout()
}
