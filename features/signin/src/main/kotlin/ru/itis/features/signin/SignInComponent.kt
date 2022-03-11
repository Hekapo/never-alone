package ru.itis.features.signin

import dagger.Component
import ru.itis.core.Feature

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

}
