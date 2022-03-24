package ru.itis.core.domain.usecase

import android.app.Activity
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.repository.PhoneSignUpRepository
import ru.itis.core.domain.viewstates.PhoneSignUpState
import javax.inject.Inject

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */


interface IPhoneSignUpUseCase {
    val phoneSignUpState: Flow<PhoneSignUpState>
    suspend fun trySignUpWithPhone(activity: Activity, phone: String)
    suspend fun verifyPhoneNumberWithCode(code: String)
    suspend fun resendCode(activity: Activity, phoneNumber: String)
}

@Reusable
internal class PhoneSignUpUseCase @Inject constructor(
    private val signUpRepository: PhoneSignUpRepository
) : IPhoneSignUpUseCase {

    override val phoneSignUpState: Flow<PhoneSignUpState>
        get() = signUpRepository.phoneSignUpProcess.distinctUntilChanged()

    override suspend fun trySignUpWithPhone(activity: Activity, phone: String) {
        signUpRepository.trySignUpWithPhone(activity, phone)
    }

    override suspend fun verifyPhoneNumberWithCode(code: String) {
        signUpRepository.verifyPhoneNumberWithCode(code)
    }

    override suspend fun resendCode(activity: Activity, phoneNumber: String) {
        signUpRepository.resendCode(activity, phoneNumber)
    }
}
