package ru.itis.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.ISignInUseCase
import javax.inject.Inject

/**
 * Created by Iskandar on 12.03.2022.
 */

internal class SignInViewModel(
    private val signInUseCase: ISignInUseCase,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {


    class SignInViewModelFactory @Inject constructor(
        private val signInUseCase: ISignInUseCase,
        private val dispatchersProvider: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignInViewModel(signInUseCase, dispatchersProvider) as T
        }
    }
}
