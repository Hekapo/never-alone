package ru.itis.main_screen.messenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.network.NetworkListener
import javax.inject.Scope

/**
 * Created by Iskandar on 04.04.2022.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MessengerScope

@[MessengerScope Component(dependencies = [MessengerDeps::class])]
internal interface MessengerComponent {

    val messengerViewModel: MessengerViewModel.MessengerViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(deps: MessengerDeps): Builder

        fun build(): MessengerComponent
    }
}

interface MessengerDeps {
    val networkListener: NetworkListener
    val dispatchersProvider: DispatchersProvider

}

internal class MessengerComponentViewModel(deps: MessengerDeps) : ViewModel() {
    val messengerComponentViewModel = DaggerMessengerComponent.builder().deps(deps).build()
}

internal class MessengerComponentViewModelFactory(
    private val deps: MessengerDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MessengerComponentViewModel(deps) as T
    }
}
