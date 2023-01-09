package com.apodaca.clean_architecture.di

import com.apodaca.clean_architecture.data.repository.AuthRepository
import com.apodaca.clean_architecture.data.repository.AuthRepositoryImp
import com.apodaca.clean_architecture.data.repository.LocalUserRepository
import com.apodaca.clean_architecture.data.repository.LocalUserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImp: AuthRepositoryImp): AuthRepository

    @Binds
    abstract fun bindLocalUserRepository(localUserRepositoryImp: LocalUserRepositoryImp): LocalUserRepository
}