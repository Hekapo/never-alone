package ru.itis.features.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.itis.core.dispathers.DispatchersProvider

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */


internal class SignUpViewModel(
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {
    private val _signUpUIState = MutableStateFlow(SignUpUIState())
    val signUpUIState = _signUpUIState.asStateFlow()
}
