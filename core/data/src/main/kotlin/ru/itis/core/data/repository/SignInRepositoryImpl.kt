package ru.itis.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.itis.core.domain.repository.ISignInRepository
import ru.itis.core.domain.viewstates.SignInState
import javax.inject.Inject

/**
 * Copyright (c) 10.03.2022 Created by Iskandar
 */


internal class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ISignInRepository {

    private val signInProcessState = MutableStateFlow(SignInState.SignInStateNone)
    override val signInProcess: Flow<SignInState>
        get() = signInProcessState.asStateFlow()


}
