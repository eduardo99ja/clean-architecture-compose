package com.apodaca.clean_architecture.domain

import android.util.Log
import com.apodaca.clean_architecture.data.model.GenericResponseModel
import com.apodaca.clean_architecture.data.model.UserModel
import com.apodaca.clean_architecture.data.repository.AuthRepository
import com.apodaca.clean_architecture.domain.model.User
import com.apodaca.clean_architecture.domain.model.toDomain
import com.apodaca.clean_architecture.domain.usecase.LogInUserUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LogInUserUseCaseTest {

    @RelaxedMockK
    private lateinit var authRepository: AuthRepository



    lateinit var logInUserUseCase: LogInUserUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        logInUserUseCase = LogInUserUseCase(authRepository)
    }

    @Test
    fun `When the user is logged in then return success`() = runBlocking {
        //Given
        val user = UserModel(email ="user", password =  "password")
        coEvery { authRepository.login(user) } returns GenericResponseModel(success = true, data = User(email = "user",), message = "")

        //When
        val response = logInUserUseCase(email = user.email!!, password = user.password!!)

        //Then
        coVerify(exactly = 1) { authRepository.login(user) }
        assert(response is LogInUserUseCase.Result.Success)
        assert((response as LogInUserUseCase.Result.Success).data == user.toDomain())


    }

    @Test
    fun `When the user is not logged in then return error`() = runBlocking {
        //Given
        val user = UserModel(email = "user", password = "password")
        coEvery { authRepository.login(user) } returns GenericResponseModel(success = false, data = null, message = "Error")

        //When
        val response = logInUserUseCase(email = user.email!!, password = user.password!!)

        //Then
        coVerify(exactly = 1) { authRepository.login(user) }
        assert(response is LogInUserUseCase.Result.Failure)
        assert((response as LogInUserUseCase.Result.Failure).message == "Error")
        assert(response.data == null)
    }
}