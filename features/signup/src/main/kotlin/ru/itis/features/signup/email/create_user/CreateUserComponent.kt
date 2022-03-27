package ru.itis.features.signup.email.create_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.itis.core.annotations.FeatureScope
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IEmailSignUpUseCase
import ru.itis.core.ui.utils.EmailPassData

/**
 * Created by Iskandar on 27.03.2022.
 */

@[FeatureScope Component(dependencies = [CreateUserDeps::class])]
interface CreateUserComponent {

    fun getViewModelFactory(): CreateUserViewModel.CreateUserViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun emailData(emailData: EmailPassData): Builder

        fun deps(createUserDeps: CreateUserDeps): Builder

        fun build(): CreateUserComponent
    }
}

interface CreateUserDeps {
    val emailSignUpUseCase: IEmailSignUpUseCase
    val dispatchersProvider: DispatchersProvider
}

internal class CreateUserComponentViewModel(deps: CreateUserDeps, emailData: EmailPassData) : ViewModel() {

    val createUserComponent = DaggerCreateUserComponent.builder().deps(deps).emailData(emailData).build()
}

internal class CreateUserComponentViewModelFactory(
    private val deps: CreateUserDeps,
    private val emailData: EmailPassData
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateUserComponentViewModel(deps, emailData) as T
    }
}
