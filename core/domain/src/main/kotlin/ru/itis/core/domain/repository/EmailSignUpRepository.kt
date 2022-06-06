package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.viewstates.EmailSignUpState
import ru.itis.core.domain.viewstates.ResultState

/**
 * Created by Iskandar on 24.03.2022.
 */
interface EmailSignUpRepository {
    val emailSignUpProcess: Flow<ResultState<String, String>>
    suspend fun createUserWithEmailAndPassword(email: String, password: String)
}
