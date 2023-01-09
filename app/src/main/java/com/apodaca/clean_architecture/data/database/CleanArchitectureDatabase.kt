package com.apodaca.clean_architecture.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apodaca.clean_architecture.data.database.dao.UserDao
import com.apodaca.clean_architecture.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class CleanArchitectureDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @JvmStatic
        fun newInstance(context: Context):CleanArchitectureDatabase{
            return Room.databaseBuilder(context,CleanArchitectureDatabase::class.java,"CleanArchitectureDatabase")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}