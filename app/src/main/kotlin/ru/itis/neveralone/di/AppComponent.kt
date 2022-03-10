package ru.itis.neveralone.di

import dagger.Component
import ru.itis.core.data.di.RepositoryModule
import ru.itis.core.domain.di.UseCaseModule
import javax.inject.Singleton

/**
 * Copyright (c) 10.03.2022 Created by Iskandar
 */

@Singleton
@Component(modules = [RepositoryModule::class, UseCaseModule::class])
interface AppComponent {
}
