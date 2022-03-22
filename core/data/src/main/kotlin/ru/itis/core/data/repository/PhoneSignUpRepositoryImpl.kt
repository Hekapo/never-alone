package ru.itis.core.data.repository

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.itis.core.data.utils.Constants
import ru.itis.core.domain.repository.IPhoneSignUpRepository
import ru.itis.core.domain.viewstates.PhoneSignUpState
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */


internal class PhoneSignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IPhoneSignUpRepository {

    private val signUpWithPhoneProcessState =
        MutableStateFlow<PhoneSignUpState>(PhoneSignUpState.None)

    override val phoneSignUpProcess: Flow<PhoneSignUpState>
        get() = signUpWithPhoneProcessState.asStateFlow()

    private var storedVerificationId: String? = ""

    private lateinit var verificationStateChangedCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override suspend fun trySignUpWithPhone(activity: Activity, phone: String) {
        signUpWithPhoneProcessState.value = PhoneSignUpState.InProcess
        verificationStateChangedCallbacks =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    storedVerificationId = verificationId
                    resendToken = token
                    signUpWithPhoneProcessState.value = PhoneSignUpState.CodeSent
                }

                override fun onVerificationCompleted(authCredential: PhoneAuthCredential) {
                    signUpWithPhoneProcessState.value = PhoneSignUpState.InProcess
                    firebaseAuth.signInWithCredential(authCredential)
                        .addOnCompleteListener {
                            signUpWithPhoneProcessState.value = PhoneSignUpState.Success
                        }
                        .addOnFailureListener {
                            signUpWithPhoneProcessState.value =
                                PhoneSignUpState.InvalidCredential(it.message)
                        }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    signUpWithPhoneProcessState.value = when (e) {
                        is FirebaseAuthInvalidCredentialsException ->
                            PhoneSignUpState.InvalidCredential(e.message)
                        is FirebaseTooManyRequestsException ->
                            PhoneSignUpState.TooManyRequests(e.message)
                        else -> {
                            PhoneSignUpState.Error
                        }
                    }
                }

            }

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)
            .setTimeout(Constants.AUTH_TIME_OUT, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(verificationStateChangedCallbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override suspend fun verifyPhoneNumberWithCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        verificationStateChangedCallbacks.onVerificationCompleted(credential)

    }
}
