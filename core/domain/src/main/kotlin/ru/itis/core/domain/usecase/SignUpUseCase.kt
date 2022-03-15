package ru.itis.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.repository.ISignUpRepository
import ru.itis.core.domain.viewstates.SignUpState
import javax.inject.Inject

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */


interface ISignUpUseCase {
    val signUpState: Flow<SignUpState>
}

internal class SignUpUseCase @Inject constructor(
    private val iSignUpRepository: ISignUpRepository
) : ISignUpUseCase {

    override val signUpState: Flow<SignUpState>
        get() = iSignUpRepository.signUpProcess.distinctUntilChanged()
}
