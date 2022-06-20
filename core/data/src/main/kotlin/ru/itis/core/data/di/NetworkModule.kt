package ru.itis.core.data.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Iskandar on 12.03.2022.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance().also {
            it.useAppLanguage()
        }
    }
}
