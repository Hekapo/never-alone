package ru.itis.features.signup.email.create_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.itis.core.domain.usecase.IEmailSignUpUseCase
import ru.itis.core.domain.viewstates.EmailSignUpState
import javax.inject.Inject

/**
 * Created by Iskandar on 27.03.2022.
 */

class CreateUserViewModel(
    private val emailUseCase: IEmailSignUpUseCase
) : ViewModel() {

    private val _emailUIState = MutableStateFlow(CreateUserUIState())
    val emailUIState = _emailUIState.asStateFlow()

    init {
        emailUseCase.emailSignUpState.onEach(this::emailState).launchIn(viewModelScope)
    }

    private fun emailState(emailSignUpState: EmailSignUpState) {
        when (emailSignUpState) {
            EmailSignUpState.None -> TODO()
            EmailSignUpState.InProcess -> TODO()
            EmailSignUpState.Complete -> TODO()
            is EmailSignUpState.Error -> TODO()
        }
    }

    class CreateUserViewModelFactory @Inject constructor(
        private val emailUseCase: IEmailSignUpUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CreateUserViewModel(emailUseCase) as T
        }
    }
}
