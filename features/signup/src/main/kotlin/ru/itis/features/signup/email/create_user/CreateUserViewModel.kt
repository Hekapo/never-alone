package ru.itis.features.signup.email.create_user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.models.User
import ru.itis.core.domain.usecase.IDatabaseUseCase
import ru.itis.core.domain.usecase.IEmailSignUpUseCase
import ru.itis.core.domain.viewstates.ResultState
import ru.itis.core.network.NetworkListener
import ru.itis.core.ui.common.checkPasswordLength
import ru.itis.core.ui.common.isFieldEmpty
import ru.itis.core.ui.utils.EmailPassData
import javax.inject.Inject

/**
 * Created by Iskandar on 27.03.2022.
 */

internal class CreateUserViewModel(
    private val emailUseCase: IEmailSignUpUseCase,
    private val databaseUseCase: IDatabaseUseCase,
    networkListener: NetworkListener,
    dispatchersProvider: DispatchersProvider,
    emailData: EmailPassData
) : ViewModel() {

    private val _emailUIState = MutableStateFlow(CreateUserUIState().copy(email = emailData.email))
    val emailUIState = _emailUIState.asStateFlow()

    init {
        emailUseCase.emailSignUpState.onEach(this::emailState).launchIn(viewModelScope)

        networkListener.networkState
            .distinctUntilChanged()
            .onEach(this::onNetwork)
            .flowOn(dispatchersProvider.IO)
            .launchIn(viewModelScope)

    }

    private fun onNetwork(isAvailable: Boolean) =
        _emailUIState.update { it.copy(internetAvailable = isAvailable) }

    private fun emailState(emailSignUpState: ResultState<String, String>) {
        when (emailSignUpState) {
            is ResultState.None -> {}
            is ResultState.InProcess -> inProcess()
            is ResultState.Success -> onComplete()
            is ResultState.Error -> {
                onError(message = emailSignUpState.message ?: "Unexpected error")
            }
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
            it.copy(couldNavigate = true, isLoading = false)
        }
    }

    private fun onError(message: String) {
        _emailUIState.update {
            it.copy(
                couldNavigate = false,
                isLoading = false,
                snackBar = CreateUserUIState.SnackBar(
                    show = true,
                    message = message,
                    isError = true
                )
            )
        }
    }

    private fun inProcess() {
        _emailUIState.update {
            it.copy(isLoading = true)
        }
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
                    showError = name.isFieldEmpty()
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
                    showError = password.checkPasswordLength()
                )
            )
        }
    }

    fun hideSnackbar() {
        _emailUIState.update {
            it.copy(snackBar = CreateUserUIState.SnackBar(show = false))
        }
    }
}

internal class CreateUserViewModelFactory @Inject constructor(
    private val emailUseCase: IEmailSignUpUseCase,
    private val emailData: EmailPassData,
    private val databaseUseCase: IDatabaseUseCase,
    private val networkListener: NetworkListener,
    private val dispatchersProvider: DispatchersProvider
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateUserViewModel(
            emailUseCase = emailUseCase,
            databaseUseCase = databaseUseCase,
            emailData = emailData,
            networkListener = networkListener,
            dispatchersProvider = dispatchersProvider
        ) as T
    }
}
