package ru.itis.features.signup.phone.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.annotations.FeatureScope
import ru.itis.core.domain.usecase.IPhoneSignUpUseCase

/**
 * Created by Iskandar on 23.03.2022.
 */

@[FeatureScope Component(dependencies = [PhoneVerificationDeps::class])]
interface PhoneVerificationComponent {

    fun getViewModelFactory(): PhoneVerificationViewModel.PhoneVerificationViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(honeVerificationDeps: PhoneVerificationDeps): Builder

        fun build(): PhoneVerificationComponent
    }
}

interface PhoneVerificationDeps {
    val singUpUseCase: IPhoneSignUpUseCase
}

internal class PhoneVerificationComponentViewModel(deps: PhoneVerificationDeps) : ViewModel() {

    val phoneVerificationDepsComponent =
        DaggerPhoneVerificationComponent.builder().deps(deps).build()
}

internal class PhoneVerificationComponentViewModelFactory(
    private val deps: PhoneVerificationDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhoneVerificationComponentViewModel(deps) as T
    }
}
