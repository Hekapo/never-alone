package ru.itis.user_form

import android.app.DatePickerDialog
import android.content.Context
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
import java.text.SimpleDateFormat
import java.util.*
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

        networkListener.networkState
            .distinctUntilChanged()
            .onEach(this::onNetwork)
            .flowOn(dispatchersProvider.IO)
            .launchIn(viewModelScope)

        databaseUseCase.userFlow.onEach(this::userState).launchIn(viewModelScope)
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
    }

    fun setBirthdayDate(date: String) {
        _userInfo.update {
            it.copy(age = 10L)
        }
        _dateOfBirth.value = date
    }

    private val dateFormat = "dd-MM-yyyy"

    private fun getCalendar(): Calendar {
        return if (_dateOfBirth.value.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }

    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(_dateOfBirth.value) as Date
        return calendar
    }

    private fun getPickedDateAsString(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        return dateFormat.format(calendar.time)
    }

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                _dateOfBirth.value = getPickedDateAsString(year, month, day)
                _userInfo.update {
                    it.copy(age = day.toLong())
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
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
                Log.e("DEBUG", userState.data.toString())
                val userData = userState.data
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
                    id = "123",
                    name = "Mike",
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
