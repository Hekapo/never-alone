package ru.itis.core.domain.repository

import android.app.Activity
import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.viewstates.PhoneSignUpState

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

interface PhoneSignUpRepository {
    val phoneSignUpProcess: Flow<PhoneSignUpState>
    suspend fun trySignUpWithPhone(activity: Activity, phone: String)
    suspend fun verifyPhoneNumberWithCode(code: String)
    suspend fun resendCode(activity: Activity, phoneNumber: String)
}
