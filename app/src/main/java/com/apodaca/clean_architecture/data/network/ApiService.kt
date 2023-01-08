package com.apodaca.clean_architecture.data.network

import com.apodaca.clean_architecture.data.model.GenericResponseModel
import com.apodaca.clean_architecture.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun login(user: UserModel): GenericResponseModel<UserModel> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.login(user)
            response.body() ?: GenericResponseModel(success = false, data = null)
        }
    }
}