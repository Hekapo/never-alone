package ru.itis.user_form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component

/**
 * Created by Iskandar on 27.05.2022.
 */

@Component(dependencies = [UserFormDeps::class])
internal interface UserFormComponent {

    val factory: UserFormViewModel.UserFormViewModelFactory

    @Component.Builder
    interface Builder {
        fun deps(deps: UserFormDeps): Builder

        val build: UserFormComponent
    }
}

interface UserFormDeps

internal class UserFormComponentViewModel(deps: UserFormDeps) : ViewModel() {
    val userFormingComponent = DaggerUserFormComponent.builder().deps(deps).build
}

internal class UserFormComponentViewModelFactory(
    private val deps: UserFormDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserFormComponentViewModel(deps) as T
    }
}
