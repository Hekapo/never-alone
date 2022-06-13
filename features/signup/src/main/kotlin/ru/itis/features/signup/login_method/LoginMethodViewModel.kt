package ru.itis.features.signup.login_method

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.network.NetworkListener
import javax.inject.Inject

internal class LoginMethodViewModel(
    networkListener: NetworkListener,
    dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val _isNetAvailable = MutableStateFlow(true)
    val isNetAvailable = _isNetAvailable.asStateFlow()

    init {
        networkListener.networkState
            .distinctUntilChanged()
            .onEach(this::onNetwork)
            .flowOn(dispatchersProvider.IO)
            .launchIn(viewModelScope)
    }

    private fun onNetwork(isAvailable: Boolean) = _isNetAvailable.update { isAvailable }

    class LoginMethodViewModelFactory @Inject constructor(
        private val networkListener: NetworkListener,
        private val dispatchersProvider: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginMethodViewModel(
                networkListener = networkListener,
                dispatchersProvider = dispatchersProvider
            ) as T
        }
    }
}
