package ru.itis.features.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.annotations.Feature
import ru.itis.core.dispathers.DispatchersProvider

/**
 * Copyright (c) 15.03.2022 Created by Iskandar
 */

@[Feature Component(dependencies = [SignUpDeps::class])]
internal interface SignUpComponent {

    @Component.Builder
    interface Builder {

        fun deps(signUpDeps: SignUpDeps): Builder

        fun build(): SignUpComponent
    }
}

interface SignUpDeps {

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