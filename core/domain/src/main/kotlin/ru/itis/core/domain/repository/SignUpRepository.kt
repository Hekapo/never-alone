package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.viewstates.SignUpState

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

interface ISignUpRepository {
    val signUpProcess: Flow<SignUpState>
}
