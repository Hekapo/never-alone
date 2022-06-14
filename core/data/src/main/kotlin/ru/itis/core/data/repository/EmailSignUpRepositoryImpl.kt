package ru.itis.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.itis.core.domain.repository.EmailSignUpRepository
import ru.itis.core.domain.viewstates.ResultState
import javax.inject.Inject

/**
 * Created by Iskandar on 24.03.2022.
 */

internal class EmailSignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : EmailSignUpRepository {

    private val signUpWithEmailAndPasswordProcessState =
        MutableStateFlow<ResultState<String, String>>(ResultState.None)

    override val emailSignUpProcess: Flow<ResultState<String, String>>
        get() = signUpWithEmailAndPasswordProcessState.asStateFlow()

    override suspend fun createUserWithEmailAndPassword(email: String, password: String) {
        signUpWithEmailAndPasswordProcessState.update { ResultState.InProcess }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                signUpWithEmailAndPasswordProcessState.update { ResultState.Success(data = "") }
            }.addOnFailureListener { ex ->
                val message = ex as FirebaseAuthException
                signUpWithEmailAndPasswordProcessState.update { ResultState.Error(message = message.errorCode) }
            }.addOnCompleteListener {
                signUpWithEmailAndPasswordProcessState.update { ResultState.None }
            }
    }
}
