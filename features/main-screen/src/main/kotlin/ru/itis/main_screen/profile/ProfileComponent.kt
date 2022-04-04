package ru.itis.main_screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component

/**
 * Copyright (c) 04.04.2022 Created by Iskandar
 */

@Component(dependencies = [ProfileDeps::class])
internal interface ProfileComponent {

    val profileViewModelFactory: ProfileViewModel.ProfileViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(deps: ProfileDeps): Builder

        fun build(): ProfileComponent
    }
}

interface ProfileDeps {

}

internal class ProfileComponentViewModel(deps: ProfileDeps) : ViewModel() {

    val profileComponent = DaggerProfileComponent.builder().deps(deps).build()
}

internal class ProfileComponentViewModelFactory(private val deps: ProfileDeps) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileComponentViewModel(deps) as T
    }
}
