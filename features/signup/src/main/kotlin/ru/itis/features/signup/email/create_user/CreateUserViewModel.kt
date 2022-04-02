package ru.itis.features.signup.email.create_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.domain.usecase.IEmailSignUpUseCase
import ru.itis.core.domain.viewstates.EmailSignUpState
import ru.itis.core.ui.utils.EmailPassData
import javax.inject.Inject

/**
 * Created by Iskandar on 27.03.2022.
 */

internal class CreateUserViewModel(
    private val emailUseCase: IEmailSignUpUseCase,
    emailData: EmailPassData
) : ViewModel() {

    private val _emailUIState = MutableStateFlow(CreateUserUIState().copy(email = emailData.email))
    val emailUIState = _emailUIState.asStateFlow()

    init {
        emailUseCase.emailSignUpState.onEach(this::emailState).launchIn(viewModelScope)
    }

    private fun emailState(emailSignUpState: EmailSignUpState) {
        when (emailSignUpState) {
            EmailSignUpState.None -> {}
            EmailSignUpState.InProcess -> inProcess()
            EmailSignUpState.Complete -> onComplete()
            is EmailSignUpState.Error -> onError()
        }
    }

    private fun onComplete() {
        _emailUIState.update {
            it.copy(couldNavigate = true)
        }

    }

    private fun onError() {
        _emailUIState.update {
            it.copy(couldNavigate = false)
        }
    }

    private fun inProcess() {

    }

    fun createUser() {
        viewModelScope.launch {
            val email = _emailUIState.value.email
            val password = _emailUIState.value.inputPassword.password
            emailUseCase.createUserWithEmailAndPassword(email = email, password = password)
        }
    }

    fun onNameChange(name: String) {
        _emailUIState.update {
            it.copy(
                inputUserName = CreateUserUIState.InputUserName(
                    name = name,
                    isFieldEnabled = true
                )
            )
        }

    }

    fun onPasswordChange(password: String) {
        _emailUIState.update {
            it.copy(
                inputPassword = CreateUserUIState.InputPassword(
                    password = password,
                    isFieldEnabled = true
                )
            )
        }

    }
}

internal class CreateUserViewModelFactory @Inject constructor(
    private val emailUseCase: IEmailSignUpUseCase,
    private val emailData: EmailPassData
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateUserViewModel(emailUseCase, emailData) as T
    }
}
