package com.apodaca.clean_architecture.data.network

import com.apodaca.clean_architecture.data.model.GenericResponseModel
import com.apodaca.clean_architecture.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {
    /**
     * Login user with credentials
     * @param user User
     * @return Response<GenericResponse<User>>
     */
    @POST(ApiConstants.wsPath + "login")
    suspend fun login(@Body user: UserModel): Response<GenericResponseModel<UserModel>>

}