package ru.itis.main_screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.itis.main_screen.profile.ProfileDeps

/**
 * Created by Iskandar on 03.04.2022.
 */

@Component(dependencies = [MainDeps::class])
internal interface MainComponent {

    @Component.Builder
    interface Builder {

        fun deps(mainDeps: MainDeps): Builder

        fun build(): MainComponent
    }
}

interface MainDeps : ProfileDeps {

}

internal class MainComponentViewModel(deps: MainDeps) : ViewModel() {
    val mainComponent = DaggerMainComponent.builder().deps(deps)
}

internal class MainComponentViewModelFactory(
    private val deps: MainDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainComponentViewModel(deps) as T
    }
}
