package ru.itis.core.data.di

import dagger.Binds
import dagger.Module
import ru.itis.core.data.repository.SignInRepositoryImpl
import ru.itis.core.data.repository.PhoneSignUpRepositoryImpl
import ru.itis.core.domain.repository.ISignInRepository
import ru.itis.core.domain.repository.PhoneSignUpRepository

/**
 * Copyright (c) 10.03.2022 Created by Iskandar
 */

@Module(includes = [RepositoryModuleBinds::class])
class RepositoryModule

@Module
internal interface RepositoryModuleBinds {

    @Binds
    fun provideSingInRepository(repository: SignInRepositoryImpl): ISignInRepository

    @Binds
    fun provideSignUpModule(repositoryImplPhone: PhoneSignUpRepositoryImpl): PhoneSignUpRepository
}
