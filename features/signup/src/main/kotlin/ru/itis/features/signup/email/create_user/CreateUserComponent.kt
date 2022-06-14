package ru.itis.features.signup.email.create_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IDatabaseUseCase
import ru.itis.core.domain.usecase.IEmailSignUpUseCase
import ru.itis.core.network.NetworkListener
import ru.itis.core.ui.utils.EmailPassData
import javax.inject.Scope

/**
 * Created by Iskandar on 27.03.2022.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CreateUserScope

@[CreateUserScope Component(dependencies = [CreateUserDeps::class])]
internal interface CreateUserComponent {

    fun getViewModelFactory(): CreateUserViewModelFactory

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
    val databaseUseCase: IDatabaseUseCase
    val dispatchersProvider: DispatchersProvider
    val networkListener: NetworkListener
}

internal class CreateUserComponentViewModel(deps: CreateUserDeps, emailData: EmailPassData) : ViewModel() {

    val createUserComponent = DaggerCreateUserComponent.builder().deps(deps).emailData(emailData).build()
}

internal class CreateUserComponentViewModelFactory(
    private val deps: CreateUserDeps,
    private val emailData: EmailPassData
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateUserComponentViewModel(deps, emailData) as T
    }
}
