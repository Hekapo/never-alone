package ru.itis.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.itis.core.domain.models.User
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

    private val _signInWithGoogleProcessState =
        MutableStateFlow<ResultState<User, String>>(ResultState.None)

    override val signInProcessState: Flow<ResultState<String, String>>
        get() = _signInProcessState.asStateFlow()

    override val signInWithGoogleProcessState: Flow<ResultState<User, String>>
        get() = _signInWithGoogleProcessState.asStateFlow()

    override suspend fun trySignInWithEmailAndPassword(email: String, password: String) {
        _signInProcessState.update { ResultState.InProcess }
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnFailureListener { ex ->
                _signInProcessState.update { ResultState.Error(ex.message) }
            }.addOnCompleteListener {
                _signInProcessState.update { ResultState.None }
            }.addOnSuccessListener {
                _signInProcessState.update { ResultState.Success(data = "") }
            }
    }

    override suspend fun signInWithGoogle(tokenId: String) {
        _signInWithGoogleProcessState.update { ResultState.InProcess }
        val credential = GoogleAuthProvider.getCredential(tokenId, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { res ->
                val user = res.user
                _signInWithGoogleProcessState.update {
                    ResultState.Success(
                        data = User(
                            id = user?.uid,
                            name = user?.displayName,
                            email = user?.email,
                            phone = user?.phoneNumber
                        )
                    )
                }
            }.addOnCompleteListener {
                _signInWithGoogleProcessState.update { ResultState.None }
            }.addOnFailureListener { ex ->
                Log.e("DEBUG", "${ex.message}")
                _signInWithGoogleProcessState.update { ResultState.Error(message = ex.message) }
            }
    }

    override suspend fun getSignedUser(): User {
        val userData = firebaseAuth.currentUser
        return User(
            id = userData?.uid,
            name = userData?.displayName,
            email = userData?.email,
            phone = userData?.phoneNumber
        )
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }
}
