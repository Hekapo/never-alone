package ru.itis.user_form

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import ru.itis.core.domain.models.User
import ru.itis.core.domain.usecase.IDatabaseUseCase
import ru.itis.core.domain.viewstates.ResultState
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Iskandar on 27.05.2022.
 */

internal class UserFormViewModel(private val databaseUseCase: IDatabaseUseCase) : ViewModel() {

    private val _userFormScreen = MutableStateFlow<UserFormScreens>(UserFormScreens.BirthScreen)
    val userFormScreen = _userFormScreen.asStateFlow()

    private val _userInfo = MutableStateFlow(User())
    val userInfo = _userInfo.asStateFlow()

    private val _dateOfBirth = MutableStateFlow("")
    val dateOfBirth = _dateOfBirth.asStateFlow()

    init {
        databaseUseCase.userFlow.onEach(this::userState).launchIn(viewModelScope)
    }

    fun setBirthdayDate(date: String) {
        _dateOfBirth.value = date
    }

    private var dateFormat = "yyyy-MM-dd"

    private fun getCalendar(): Calendar {
        return if (_dateOfBirth.value.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }

    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(_dateOfBirth.value)
        return calendar
    }

    private fun getPickedDateAsString(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat)
        return dateFormat.format(calendar.time)
    }

    fun showDatePickerDialog(context: Context) {
        val calendar = getCalendar()
        DatePickerDialog(
            context, { _, year, month, day ->
                _dateOfBirth.value = getPickedDateAsString(year, month, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun userState(userState: ResultState<User, Any>) {
        when (userState) {
            is ResultState.None -> {}
            is ResultState.InProcess -> {}
            is ResultState.Error -> {}
            is ResultState.Success -> {
                val userData = userState.data
                _userInfo.update {
                    it.copy(
                        name = userData.name,
                    )
                }
            }
        }
    }

    fun navigateBack() {
        _userFormScreen.update {
            when (it) {
                UserFormScreens.BirthScreen -> {
                    UserFormScreens.BirthScreen
                }
                is UserFormScreens.GenderScreen -> {
                    UserFormScreens.BirthScreen
                }
                is UserFormScreens.InterestsScreen -> {
                    UserFormScreens.GenderScreen
                }
                is UserFormScreens.AddPhotoScreen -> {
                    UserFormScreens.None
                }
                else -> {
                    UserFormScreens.None
                }
            }
        }
    }

    fun navigateForward() {
        _userFormScreen.update {
            when (it) {
                UserFormScreens.BirthScreen -> {
                    UserFormScreens.GenderScreen
                }
                is UserFormScreens.GenderScreen -> {
                    UserFormScreens.InterestsScreen
                }
                is UserFormScreens.InterestsScreen -> {
                    UserFormScreens.AddPhotoScreen
                }
                is UserFormScreens.AddPhotoScreen -> {
                    UserFormScreens.None
                }
                else -> {
                    UserFormScreens.None
                }
            }
        }
    }

    class UserFormViewModelFactory @Inject constructor(
        private val databaseUseCase: IDatabaseUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserFormViewModel(databaseUseCase) as T
        }
    }

}
