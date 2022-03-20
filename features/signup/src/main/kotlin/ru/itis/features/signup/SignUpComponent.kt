package ru.itis.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.annotations.FeatureScope
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.ISignUpUseCase

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

@[FeatureScope Component(dependencies = [SignUpDeps::class])]
internal interface SignUpComponent {

    fun getViewModelFactory(): SignUpViewModel.SignUpViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(signUpDeps: SignUpDeps): Builder

        fun build(): SignUpComponent
    }
}

interface SignUpDeps {

    val singUpUseCase: ISignUpUseCase
    val dispatchersProvider: DispatchersProvider
}

internal class SignUpComponentViewModel(deps: SignUpDeps) : ViewModel() {

    val signUpComponent = DaggerSignUpComponent.builder().deps(deps).build()
}

internal class SignUpComponentViewModelFactory(
    private val deps: SignUpDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpComponentViewModel(deps) as T
    }
}
