package ru.itis.core.data.di

import dagger.Binds
import dagger.Module
import ru.itis.core.data.repository.EmailSignUpRepositoryImpl
import ru.itis.core.data.repository.PhoneSignUpRepositoryImpl
import ru.itis.core.data.repository.SignInRepositoryImpl
import ru.itis.core.domain.repository.EmailSignUpRepository
import ru.itis.core.domain.repository.PhoneSignUpRepository
import ru.itis.core.domain.repository.SignInRepository

/**
 * Copyright (c) 10.03.2022 Created by Iskandar
 */

@Module(includes = [RepositoryModuleBinds::class])
class RepositoryModule

@Module
internal interface RepositoryModuleBinds {

    @Binds
    fun provideSignInRepository(
        repository: SignInRepositoryImpl
    ): SignInRepository

    @Binds
    fun providePhoneSignUpRepository(
        repositoryImplPhone: PhoneSignUpRepositoryImpl
    ): PhoneSignUpRepository

    @Binds
    fun provideEmailSignUpRepository(
        emailSignUpRepositoryImpl: EmailSignUpRepositoryImpl
    ): EmailSignUpRepository
}
