package ru.itis.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IDatastoreUseCase
import javax.inject.Inject

/**
 * Created by Iskandar on 26.05.2022.
 */

internal class OnBoardingViewModel(
    private val datastoreUseCase: IDatastoreUseCase,
    private val dispatcher: DispatchersProvider
) : ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(dispatcher.IO) {
            datastoreUseCase.saveOnBoardingState(completed = completed)
        }
    }

    class OnBoardingViewModelFactory @Inject constructor(
        private val datastoreUseCase: IDatastoreUseCase,
        private val dispatcher: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OnBoardingViewModel(datastoreUseCase, dispatcher) as T
        }
    }
}
