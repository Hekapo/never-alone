package ru.itis.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IDatabaseUseCase
import ru.itis.core.domain.usecase.IDatastoreUseCase
import ru.itis.core.network.NetworkListener

/**
 * Created by Iskandar on 26.05.2022.
 */

@Component(dependencies = [LoadingDeps::class])
internal interface LoadingScreenComponent {

    val viewModeFactory: LoadingViewModel.LoadingViewModelFactory

    @Component.Builder
    interface Builder {
        fun deps(deps: LoadingDeps): Builder

        val build: LoadingScreenComponent
    }
}

interface LoadingDeps {
    val datastoreUseCase: IDatastoreUseCase
    val databaseUseCase: IDatabaseUseCase
    val dispatchersProvider: DispatchersProvider
    val networkListener: NetworkListener
}

internal class LoadingComponentViewModel(deps: LoadingDeps) : ViewModel() {

    val loadingComponent = DaggerLoadingScreenComponent.builder().deps(deps).build
}

internal class LoadingComponentViewModelFactory(
    private val deps: LoadingDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoadingComponentViewModel(deps) as T
    }
}
