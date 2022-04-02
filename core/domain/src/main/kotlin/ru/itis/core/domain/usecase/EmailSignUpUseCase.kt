package ru.itis.core.domain.usecase

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.repository.EmailSignUpRepository
import ru.itis.core.domain.viewstates.EmailSignUpState
import javax.inject.Inject

/**
 * Created by Iskandar on 24.03.2022.
 */

interface IEmailSignUpUseCase {
    val emailSignUpState: Flow<EmailSignUpState>
    suspend fun createUserWithEmailAndPassword(email: String, password: String)
}

@Reusable
class EmailSignUpUseCase @Inject constructor(
    private val signUpRepository: EmailSignUpRepository
) : IEmailSignUpUseCase {

    override val emailSignUpState: Flow<EmailSignUpState>
        get() = signUpRepository.emailSignUpProcess.distinctUntilChanged()

    override suspend fun createUserWithEmailAndPassword(email: String, password: String) {
        signUpRepository.createUserWithEmailAndPassword(email, password)
    }
}
