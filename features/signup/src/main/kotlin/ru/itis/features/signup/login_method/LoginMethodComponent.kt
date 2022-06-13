package ru.itis.features.signup.login_method

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.network.NetworkListener

@Component(dependencies = [LoginMethodDeps::class])
internal interface LoginMethodComponent {

    val viewModelFactory: LoginMethodViewModel.LoginMethodViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(deps: LoginMethodDeps): Builder

        val build: LoginMethodComponent
    }
}

interface LoginMethodDeps {
    val networkListener: NetworkListener
    val dispatchersProvider: DispatchersProvider
}

internal class LoginMethodComponentViewModel(deps: LoginMethodDeps) : ViewModel() {

    val loginMethodComponent = DaggerLoginMethodComponent.builder().deps(deps).build
}

internal class LoginMethodComponentViewModelFactory(
    private val deps: LoginMethodDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginMethodComponentViewModel(deps) as T
    }
}
