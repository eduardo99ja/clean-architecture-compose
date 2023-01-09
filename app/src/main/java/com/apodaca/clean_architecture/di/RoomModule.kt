package com.apodaca.clean_architecture.di

import android.content.Context
import com.apodaca.clean_architecture.data.database.CleanArchitectureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) : CleanArchitectureDatabase {
        return CleanArchitectureDatabase.newInstance(context)
    }

    @Singleton
    @Provides
    fun provideUserDao(db: CleanArchitectureDatabase) = db.userDao()
}