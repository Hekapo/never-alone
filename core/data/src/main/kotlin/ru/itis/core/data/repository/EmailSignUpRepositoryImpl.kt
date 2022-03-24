package ru.itis.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.itis.core.domain.repository.EmailSignUpRepository
import ru.itis.core.domain.viewstates.EmailSignUpState
import javax.inject.Inject

/**
 * Created by Iskandar on 24.03.2022.
 */

internal class EmailSignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : EmailSignUpRepository {

    private val signUpWithEmailAndPasswordProcessState =
        MutableStateFlow<EmailSignUpState>(EmailSignUpState.None)

    override val emailSignUpProcess: Flow<EmailSignUpState>
        get() = signUpWithEmailAndPasswordProcessState.asStateFlow()

    override suspend fun createUserWithEmailAndPassword(email: String, password: String) {
        signUpWithEmailAndPasswordProcessState.value = EmailSignUpState.InProcess
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                signUpWithEmailAndPasswordProcessState.value = EmailSignUpState.Complete
            }.addOnFailureListener {
                signUpWithEmailAndPasswordProcessState.value = EmailSignUpState.Error(it.message)
            }
    }
}
