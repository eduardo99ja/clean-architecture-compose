package com.apodaca.clean_architecture.data.repository

import androidx.lifecycle.LiveData
import com.apodaca.clean_architecture.data.database.dao.UserDao
import com.apodaca.clean_architecture.data.database.entities.UserEntity
import com.apodaca.clean_architecture.domain.model.User
import com.apodaca.clean_architecture.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserRepositoryImp @Inject constructor(
    private val userDao: UserDao
) : LocalUserRepository {
    override suspend fun upsert(user: UserEntity): Long {
        return userDao.upsert(user)
    }

    override fun get(): Flow<UserEntity?> {
        return userDao.get()

    }

    override fun getWithLiveData(): LiveData<UserEntity?> {
       return userDao.getWithLiveData()

    }

    override suspend fun deleteAll() {
        return userDao.deleteAll()
    }

    override suspend fun update(userDB: UserEntity) {
        return userDao.update(userDB)
    }
}
