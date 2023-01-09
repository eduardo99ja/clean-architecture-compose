package com.apodaca.clean_architecture.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.apodaca.clean_architecture.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: UserEntity): Long

    @Query("SELECT * FROM user LIMIT 1")
    fun get(): Flow<UserEntity?>

    @Query("SELECT * FROM user LIMIT 1")
    fun getWithLiveData(): LiveData<UserEntity?>

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Update
    suspend fun update(userDB: UserEntity)
}