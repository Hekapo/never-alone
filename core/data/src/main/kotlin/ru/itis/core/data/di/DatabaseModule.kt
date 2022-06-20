package ru.itis.core.data.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import ru.itis.core.data.BuildConfig
import javax.inject.Singleton

/**
 * Created by Iskandar on 10.04.2022.
 */

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance(BuildConfig.DATABASE_URL)
    }

    @Provides
    @Singleton
    internal fun provideDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.reference
    }

}
