package ru.itis.features.signup.email.create_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.annotations.FeatureScope
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IEmailSignUpUseCase

/**
 * Created by Iskandar on 27.03.2022.
 */

@[FeatureScope Component(dependencies = [CreateUserDeps::class])]
interface CreateUserComponent {

    fun getViewModelFactory(): CreateUserViewModel.CreateUserViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(createUserDeps: CreateUserDeps): Builder

        fun build(): CreateUserComponent
    }
}

interface CreateUserDeps {
    val emailSignUpUseCase: IEmailSignUpUseCase
    val dispatchersProvider: DispatchersProvider

}

internal class CreateUserComponentViewModel(deps: CreateUserDeps) : ViewModel() {

    val createUserComponent = DaggerCreateUserComponent.builder().deps(deps).build()
}

internal class CreateUserComponentViewModelFactory(
    private val deps: CreateUserDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateUserComponentViewModel(deps) as T
    }
}
