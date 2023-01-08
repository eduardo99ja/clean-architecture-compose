package com.apodaca.clean_architecture.data.repository

import com.apodaca.clean_architecture.data.model.GenericResponseModel
import com.apodaca.clean_architecture.data.model.UserModel
import com.apodaca.clean_architecture.domain.model.User

interface AuthRepository {
    suspend fun login(user: UserModel): GenericResponseModel<User>
}