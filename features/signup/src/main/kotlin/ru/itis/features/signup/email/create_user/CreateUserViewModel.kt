package ru.itis.features.signup.email.create_user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.domain.models.User
import ru.itis.core.domain.usecase.IDatabaseUseCase
import ru.itis.core.domain.usecase.IEmailSignUpUseCase
import ru.itis.core.domain.viewstates.EmailSignUpState
import ru.itis.core.ui.utils.EmailPassData
import javax.inject.Inject

/**
 * Created by Iskandar on 27.03.2022.
 */

internal class CreateUserViewModel(
    private val emailUseCase: IEmailSignUpUseCase,
    private val databaseUseCase: IDatabaseUseCase,
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
        viewModelScope.launch {
            databaseUseCase.addUser(
                User(
                    id = databaseUseCase.getCurrentUserId(),
                    name = _emailUIState.value.inputUserName.name,
                    email = _emailUIState.value.email
                )
            )
        }
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
                    isFieldEnabled = true,
                    showError = name.isEmpty()
                )
            )
        }

    }

    fun onPasswordChange(password: String) {
        _emailUIState.update {
            it.copy(
                inputPassword = CreateUserUIState.InputPassword(
                    password = password,
                    isFieldEnabled = true,
                    showError = password.isEmpty()
                )
            )
        }

    }
}

internal class CreateUserViewModelFactory @Inject constructor(
    private val emailUseCase: IEmailSignUpUseCase,
    private val emailData: EmailPassData,
    private val databaseUseCase: IDatabaseUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateUserViewModel(
            emailUseCase = emailUseCase,
            databaseUseCase = databaseUseCase,
            emailData = emailData
        ) as T
    }
}
