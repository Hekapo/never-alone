package ru.itis.neveralone.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.itis.core.data.di.NetworkModule
import ru.itis.core.data.di.RepositoryModule
import ru.itis.core.di.CoroutineModule
import ru.itis.core.domain.di.UseCaseModule
import ru.itis.core.domain.usecase.IPhoneSignUpUseCase
import ru.itis.core.domain.usecase.ISignInUseCase
import ru.itis.features.signin.SignInDeps
import ru.itis.features.signup.SignUpDeps
import ru.itis.features.signup.email.create_user.CreateUserDeps
import ru.itis.features.signup.phone.verification.PhoneVerificationDeps
import javax.inject.Singleton

/**
 * Copyright (c) 10.03.2022 Created by Iskandar
 */

@[Singleton Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class,
        CoroutineModule::class,
        NetworkModule::class]
)]
interface AppComponent : SignInDeps, SignUpDeps, PhoneVerificationDeps, CreateUserDeps {

    override val sigInUseCase: ISignInUseCase
    override val singUpUseCase: IPhoneSignUpUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
