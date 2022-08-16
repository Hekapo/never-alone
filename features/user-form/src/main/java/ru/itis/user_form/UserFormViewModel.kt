package ru.itis.user_form

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.models.User
import ru.itis.core.domain.usecase.IDatabaseUseCase
import ru.itis.core.domain.viewstates.ResultState
import ru.itis.core.network.NetworkListener
import ru.itis.core.ui.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Created by Iskandar on 27.05.2022.
 */

internal class UserFormViewModel(
    private val databaseUseCase: IDatabaseUseCase,
    private val dispatchersProvider: DispatchersProvider,
    networkListener: NetworkListener
) : ViewModel() {

    private val _userInfo = MutableStateFlow(User())
    val userInfo = _userInfo.asStateFlow()

    private val _userFormUIState = MutableStateFlow(UserFormUIState())
    val userFormUIState = _userFormUIState.asStateFlow()

    private val _dateOfBirth = MutableStateFlow("")
    val dateOfBirth = _dateOfBirth.asStateFlow()

    private val _genderFlow = MutableStateFlow("")
    val genderFlow = _genderFlow.asStateFlow()

    private val _interests = MutableStateFlow(listOf<String>())
    val interests = _interests.asStateFlow()

    init {
        viewModelScope.launch {
            databaseUseCase.fetchCurrentUser()
            _userInfo.update {
                it.copy(id = databaseUseCase.getCurrentUserId())
            }
        }
        _userInfo.onEach(this::onAgeCheck).launchIn(viewModelScope)

        networkListener.networkState
            .distinctUntilChanged()
            .onEach(this::onNetwork)
            .flowOn(dispatchersProvider.IO)
            .launchIn(viewModelScope)

        databaseUseCase.userFlow.onEach(this::userState).launchIn(viewModelScope)
    }

    val genders = listOf(R.string.man, R.string.woman)

    private fun onAgeCheck(user: User?) {
        user?.age?.let { age ->
            if (age < 18) {
                _userFormUIState.update {
                    it.copy(
                        snackBar = UserFormUIState.SnackBar(
                            show = true,
                            isError = true,
                            message = "Ограничение по возрасту"
                        )
                    )
                }
            }
        }
    }

    private fun onNetwork(isAvailable: Boolean) {
        _userFormUIState.update {
            it.copy(
                networkAvailable = isAvailable
            )
        }
    }

    fun fillInterests(interests: List<String>) {
        _userInfo.update {
            it.copy(interests = interests)
        }
        _interests.value += interests
    }

    fun setGender(gender: String) {
        _userInfo.update {
            it.copy(sex = gender)
        }
        Log.e("DEBUG", _userInfo.value.toString())
    }

    fun setBirthdayDate(date: String) {
        val localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val nowYear = LocalDate.now().year
        val userYear = localDate.year
        val age = (nowYear - userYear).toLong()
        _userInfo.update {
            it.copy(age = age)
        }
        _dateOfBirth.value = date
    }

    private fun userState(userState: ResultState<User, Any>) {
        when (userState) {
            is ResultState.None -> Log.e("DEBUG", "None")
            is ResultState.InProcess -> {
                Log.e("DEBUG", "InProcess")
            }
            is ResultState.Error -> {
                Log.e("DEBUG", "Error")
            }
            is ResultState.Success -> {
                val userData = userState.data
                Log.e("DEBUG", "userdata $userData")
                _userInfo.update {
                    it.copy(
                        name = userData.name,
                    )
                }
            }
        }
    }

    fun updateUserData() {
        viewModelScope.launch {
            databaseUseCase.updateUser(
                User(
                    age = _userInfo.value.age,
                    isUserFormCompleted = true,
                    sex = _userInfo.value.sex ?: "Мужчина"
                )
            )
        }
    }

    fun hideSnackbar() {
        _userFormUIState.update {
            it.copy(snackBar = UserFormUIState.SnackBar(show = false))
        }
    }

    class UserFormViewModelFactory @Inject constructor(
        private val databaseUseCase: IDatabaseUseCase,
        private val dispatchersProvider: DispatchersProvider,
        private val networkListener: NetworkListener
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserFormViewModel(databaseUseCase, dispatchersProvider, networkListener) as T
        }
    }
}
