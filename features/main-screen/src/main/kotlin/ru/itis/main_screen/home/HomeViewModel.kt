package ru.itis.main_screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import ru.itis.core.EventHandler
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.network.NetworkListener
import ru.itis.main_screen.home.models.HomeEvent
import ru.itis.main_screen.home.models.HomeViewState
import javax.inject.Inject

/**
 * Created by Iskandar on 08.04.2022.
 */

internal class HomeViewModel(
    networkListener: NetworkListener,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel(), EventHandler<HomeEvent> {

    private val _homeViewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val homeViewState = _homeViewState.asStateFlow()

    init {
        networkListener.networkState
            .distinctUntilChanged()
            .onEach(this::onNetwork)
            .flowOn(dispatchersProvider.IO)
            .launchIn(viewModelScope)
    }

    private fun onNetwork(available: Boolean) {

    }

    override fun obtainEvent(event: HomeEvent) {
        when (val currentViewState = _homeViewState.value) {
            is HomeViewState.Loading -> {}
            is HomeViewState.Error -> {}
            is HomeViewState.NoInternet -> {}
            is HomeViewState.Display -> {}
        }

    }

    internal class HomeViewModelFactory @Inject constructor(
        private val networkListener: NetworkListener,
        private val dispatchersProvider: DispatchersProvider
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(networkListener, dispatchersProvider) as T
        }
    }
}
