package ru.itis.core.domain.di

import dagger.Binds
import dagger.Module
import ru.itis.core.domain.usecase.ISignInUseCase
import ru.itis.core.domain.usecase.ISignUpUseCase
import ru.itis.core.domain.usecase.SignInUseCase
import ru.itis.core.domain.usecase.SignUpUseCase

/**
 * Copyright (c) 10.03.2022 Created by Iskandar
 */

@Module(includes = [UseCaseModuleBinds::class])
class UseCaseModule

@Module
internal interface UseCaseModuleBinds {

    @Binds
    fun provideSignInUseCase(useCase: SignInUseCase): ISignInUseCase

    @Binds
    fun provideSignUpUseCase(useCase: SignUpUseCase): ISignUpUseCase
}
