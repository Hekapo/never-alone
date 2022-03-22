package ru.itis.core.domain.usecase

import android.app.Activity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.repository.IPhoneSignUpRepository
import ru.itis.core.domain.viewstates.PhoneSignUpState
import javax.inject.Inject

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */


interface IPhoneSignUpUseCase {
    val phoneSignUpState: Flow<PhoneSignUpState>
    suspend fun trySignUpWithPhone(activity: Activity, phone: String)
    suspend fun verifyPhoneNumberWithCode(code: String)
}

internal class PhoneSignUpUseCase @Inject constructor(
    private val iSignUpRepository: IPhoneSignUpRepository
) : IPhoneSignUpUseCase {

    override val phoneSignUpState: Flow<PhoneSignUpState>
        get() = iSignUpRepository.phoneSignUpProcess.distinctUntilChanged()

    override suspend fun trySignUpWithPhone(activity: Activity, phone: String) {
        iSignUpRepository.trySignUpWithPhone(activity, phone)
    }

    override suspend fun verifyPhoneNumberWithCode(code: String) {
        iSignUpRepository.verifyPhoneNumberWithCode(code)
    }
}
