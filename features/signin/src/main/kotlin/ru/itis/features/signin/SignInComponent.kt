package ru.itis.features.signin

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import dagger.Component
import ru.itis.core.annotations.Feature
import ru.itis.core.domain.usecase.ISignInUseCase
import kotlin.properties.Delegates.notNull


/**
 * Created by Iskandar on 11.03.2022.
 */

@[Feature Component(dependencies = [SignInDeps::class])]
internal interface SignInComponent {

    @Component.Builder
    interface Builder {

        fun deps(signInDeps: SignInDeps): Builder

        fun build(): SignInComponent
    }
}

interface SignInDeps {

    val sigInUseCase: ISignInUseCase
}

interface SignInDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: SignInDeps

    companion object : SignInDepsProvider by SignInDepsStore
}

object SignInDepsStore : SignInDepsProvider {

    override var deps: SignInDeps by notNull()
}

internal class SignInComponentViewModel : ViewModel() {

    val signInComponent = DaggerSignInComponent.builder().deps(SignInDepsProvider.deps).build()
}
