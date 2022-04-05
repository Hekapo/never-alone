package ru.itis.main_screen.main

import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.itis.main_screen.main.destinations.MainBottomScreen
import javax.inject.Inject

/**
 * Created by Iskandar on 03.04.2022.
 */

internal class MainViewModel() : ViewModel() {

    private val _navigation: MutableStateFlow<MainBottomScreen> =
        MutableStateFlow(MainBottomScreen.Home)
    val navigation: StateFlow<MainBottomScreen> = _navigation


    fun onRouteChange(route: MainBottomScreen) {

    }


    internal class MainViewModelFactory @Inject constructor(

    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel() as T
        }
    }
}
