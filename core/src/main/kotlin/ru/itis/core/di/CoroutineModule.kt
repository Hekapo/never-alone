package ru.itis.core.di

import dagger.Module
import dagger.Provides
import ru.itis.core.dispathers.DispatchersProvider

/**
 * Copyright (c) 11.03.2022 Created by Iskandar
 */

@Module
class CoroutineModule {

    @Provides
    fun provideDispatchers(): DispatchersProvider {
        return DispatchersProvider()
    }

}
