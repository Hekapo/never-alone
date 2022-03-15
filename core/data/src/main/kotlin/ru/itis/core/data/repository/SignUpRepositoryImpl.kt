package ru.itis.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.itis.core.domain.repository.ISignUpRepository
import ru.itis.core.domain.viewstates.SignUpState
import javax.inject.Inject

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */


internal class SignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ISignUpRepository {

    private val signUpProcessState = MutableStateFlow<SignUpState>(SignUpState.SignUpStateNone)

    override val signUpProcess: Flow<SignUpState>
        get() = signUpProcessState.asStateFlow()
}
