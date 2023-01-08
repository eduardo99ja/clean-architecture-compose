package com.apodaca.clean_architecture.data.repository

import com.apodaca.clean_architecture.data.model.GenericResponseModel
import com.apodaca.clean_architecture.data.model.UserModel
import com.apodaca.clean_architecture.data.network.ApiService
import com.apodaca.clean_architecture.domain.model.User
import com.apodaca.clean_architecture.domain.model.toDomain
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun login(user: UserModel): GenericResponseModel<User> {
        val response = apiService.login(user)
        return response.let { response ->
            GenericResponseModel(
                success = response.success,
                data = response.data?.toDomain(),
                message = response.message,
                code = response.code
            )
        }
    }
}
