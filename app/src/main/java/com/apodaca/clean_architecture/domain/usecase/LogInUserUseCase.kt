package com.apodaca.clean_architecture.domain.usecase

import com.apodaca.clean_architecture.data.database.entities.toDatabase
import com.apodaca.clean_architecture.data.model.GenericResponseModel
import com.apodaca.clean_architecture.data.model.UserModel
import com.apodaca.clean_architecture.data.repository.AuthRepository
import com.apodaca.clean_architecture.data.repository.LocalUserRepository
import com.apodaca.clean_architecture.domain.model.User
import timber.log.Timber
import javax.inject.Inject

class LogInUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val addUserToDatabaseUseCase: AddUserToDatabaseUseCase
) {
    suspend operator fun invoke(email: String, password: String): Result {
        Timber.d("invoke: $email $password")
        val userModel = UserModel(email = email, password = password)

        return try {
            val response = authRepository.login(userModel)
            if (response.success) {
                Timber.d("Success!")
                addUserToDatabaseUseCase(response.data!!.toDatabase())
                Result.Success(response.data!!)
            } else {
                Timber.e("LogInUserUseCase: ${response.message}")
                Result.Failure(response.message)
            }
        } catch (e: Exception) {
            Timber.e("LogInUserUseCase: failed, exception: ${e.message}")
            Result.Failure(e.message!!)
        }

    }

    sealed class Result(val data: User? = null,val message: String? = null) {
        class Success(data: User) : Result(data)
        class Failure(message: String, data: User? = null) : Result(data, message)
    }

}
