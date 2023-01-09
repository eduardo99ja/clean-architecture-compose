package com.apodaca.clean_architecture.data.repository

import androidx.lifecycle.LiveData
import com.apodaca.clean_architecture.data.database.entities.UserEntity
import com.apodaca.clean_architecture.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository {
    suspend fun upsert(user: UserEntity): Long
    fun get(): Flow<UserEntity?>
    fun getWithLiveData(): LiveData<UserEntity?>
    suspend fun deleteAll()
    suspend fun update(userDB: UserEntity)
}