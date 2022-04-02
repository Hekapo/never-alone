package ru.itis.core.domain.di

import dagger.Binds
import dagger.Module
import ru.itis.core.domain.usecase.*

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
    fun providePhoneSignUpUseCase(useCasePhone: PhoneSignUpUseCase): IPhoneSignUpUseCase

    @Binds
    fun provideEmailSignUpUseCase(useCaseEmail: EmailSignUpUseCase): IEmailSignUpUseCase
}
