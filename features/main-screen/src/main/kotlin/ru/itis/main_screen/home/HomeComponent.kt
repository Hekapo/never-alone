package ru.itis.main_screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.network.NetworkListener
import javax.inject.Scope

/**
 * Created by Iskandar on 08.04.2022.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class HomeScope

@[HomeScope Component(dependencies = [HomeDeps::class])]
internal interface HomeComponent {

    val homeViewModelFactory: HomeViewModel.HomeViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(deps: HomeDeps): Builder

        val build: HomeComponent
    }
}

interface HomeDeps {
    val networkListener: NetworkListener
    val dispatchersProvider: DispatchersProvider

}

internal class HomeComponentViewModel(deps: HomeDeps) : ViewModel() {
    val homeComponentViewModel = DaggerHomeComponent.builder().deps(deps).build
}

internal class HomeComponentViewModelFactory(
    private val deps: HomeDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeComponentViewModel(deps) as T
    }
}
