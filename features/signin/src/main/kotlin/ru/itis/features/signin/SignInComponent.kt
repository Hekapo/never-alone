package ru.itis.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.ISignInUseCase
import javax.inject.Scope


/**
 * Created by Iskandar on 11.03.2022.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class SignInScope

@[SignInScope Component(dependencies = [SignInDeps::class])]
internal interface SignInComponent {

    fun getViewModelFactory(): SignInViewModel.SignInViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(signInDeps: SignInDeps): Builder

        fun build(): SignInComponent
    }
}

interface SignInDeps {

    val sigInUseCase: ISignInUseCase
    val dispatchersProvider: DispatchersProvider
}

internal class SignInComponentViewModel(deps: SignInDeps) : ViewModel() {

    val signInComponent = DaggerSignInComponent.builder().deps(deps).build()
}

internal class SignInComponentViewModelFactory(
    private val deps: SignInDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignInComponentViewModel(deps) as T
    }
}
