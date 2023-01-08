package com.apodaca.clean_architecture.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apodaca.clean_architecture.data.model.UserModel
import com.apodaca.clean_architecture.domain.model.User
import com.apodaca.clean_architecture.domain.model.toDomain
import com.apodaca.clean_architecture.domain.usecase.LogInUserUseCase
import com.apodaca.clean_architecture.ui.viewmodel.LoginViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @RelaxedMockK
    private lateinit var logInUserUseCase: LogInUserUseCase

    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        loginViewModel = LoginViewModel(logInUserUseCase = logInUserUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when logInUserUseCase return a user set on the livedata`() {
        //Given
        val user = UserModel(email = "eduardo@gmail.com", password = "1234")
        coEvery { logInUserUseCase(email = user.email!!, password = user.password!!) } returns LogInUserUseCase.Result.Success(data = User(email = user.email!!))

        //When
        loginViewModel.loginClicked(email = user.email!!, password = user.password!!)

        //Then
        assert(loginViewModel.user.value == user.toDomain())
    }


}