package ru.itis.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.itis.core.domain.repository.SignInRepository
import ru.itis.core.domain.viewstates.ResultState
import javax.inject.Inject

/**
 * Copyright (c) 10.03.2022 Created by Iskandar
 */

internal class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : SignInRepository {

    private val _signInProcessState =
        MutableStateFlow<ResultState<String, String>>(ResultState.None)

    override val signInProcessState: Flow<ResultState<String, String>>
        get() = _signInProcessState.asStateFlow()

    override suspend fun trySignInWithEmailAndPassword(email: String, password: String) {
        _signInProcessState.update { ResultState.InProcess }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnFailureListener { ex ->
                _signInProcessState.update { ResultState.Error(ex.message) }
            }.addOnCompleteListener {
                _signInProcessState.update { ResultState.None }
            }.addOnSuccessListener {
                _signInProcessState.update { ResultState.Success(data = "") }
            }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }
}
