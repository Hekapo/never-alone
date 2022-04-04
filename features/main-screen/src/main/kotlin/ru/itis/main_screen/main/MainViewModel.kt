package ru.itis.main_screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Iskandar on 03.04.2022.
 */

internal class MainViewModel(

): ViewModel() {



    internal class MainViewModelFactory(

    ):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel() as T
        }
    }
}
