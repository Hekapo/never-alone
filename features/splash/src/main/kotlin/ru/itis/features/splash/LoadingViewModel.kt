package ru.itis.features.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IDatabaseUseCase
import ru.itis.core.domain.usecase.IDatastoreUseCase
import javax.inject.Inject

/**
 * Created by Iskandar on 26.05.2022.
 */

internal class LoadingViewModel(
    private val databaseUseCase: IDatabaseUseCase,
    private val datastoreUseCase: IDatastoreUseCase,
    dispatcher: DispatchersProvider
) : ViewModel() {

    private val _navigateMain = MutableStateFlow(false)
    val navigateMain = _navigateMain.asStateFlow()

    init {
        viewModelScope.launch(dispatcher.IO) {
            val currentUser = databaseUseCase.getCurrentUserId()
            datastoreUseCase.readOnBoardingState().collect { completed ->
                Log.e("DEBUG", "onBoarding completed: $completed")
                if (completed && currentUser != null) {
                    _navigateMain.update { true }
                } else {
                    _navigateMain.update { false }
                }
            }
        }
    }

    class LoadingViewModelFactory @Inject constructor(
        private val databaseUseCase: IDatabaseUseCase,
        private val datastoreUseCase: IDatastoreUseCase,
        private val dispatcher: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoadingViewModel(databaseUseCase, datastoreUseCase, dispatcher) as T
        }
    }
}
