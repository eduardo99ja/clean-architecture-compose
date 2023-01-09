package com.apodaca.clean_architecture.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apodaca.clean_architecture.domain.model.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    var id: Long? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "api_token")
    var apiToken: String? = null,
)

fun User.toDatabase(): UserEntity = UserEntity(
    name = name,
    apiToken = apiToken,
)