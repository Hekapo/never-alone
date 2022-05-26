package ru.itis.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IDatastoreUseCase
import ru.itis.core.network.NetworkListener

/**
 * Created by Iskandar on 26.05.2022.
 */

@Component(dependencies = [OnBoardingDeps::class])
internal interface OnBoardingComponent {

    val factory: OnBoardingViewModel.OnBoardingViewModelFactory

    @Component.Builder
    interface Builder {
        fun deps(deps: OnBoardingDeps): Builder

        val build: OnBoardingComponent
    }
}

interface OnBoardingDeps {
    val networkListener: NetworkListener
    val dispatchersProvider: DispatchersProvider
    val datastoreUseCase: IDatastoreUseCase
}

internal class OnBoardingComponentViewModel(deps: OnBoardingDeps) : ViewModel() {
    val onBoardingComponent = DaggerOnBoardingComponent.builder().deps(deps).build
}

internal class OnBoardingComponentViewModelFactory(
    private val deps: OnBoardingDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnBoardingComponentViewModel(deps) as T
    }
}
