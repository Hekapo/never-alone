package ru.itis.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IPhoneSignUpUseCase
import javax.inject.Scope

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class SignUpScope

@[SignUpScope Component(dependencies = [SignUpDeps::class])]
internal interface SignUpComponent {

    fun getViewModelFactory(): SignUpViewModel.SignUpViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(signUpDeps: SignUpDeps): Builder

        fun build(): SignUpComponent
    }
}

interface SignUpDeps {

    val singUpUseCase: IPhoneSignUpUseCase
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
