package com.apodaca.clean_architecture.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apodaca.clean_architecture.domain.model.User
import com.apodaca.clean_architecture.domain.usecase.LogInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase
) : ViewModel() {

    /**
     * Private variables
     */
    private val _email = mutableStateOf("")
    private val _password = mutableStateOf("")

    /**
     * Public variables
     */

    val email: State<String>
        get() = _email
    val password: State<String>
        get() = _password

    val isLoading = MutableLiveData<Boolean>()
    val user = MutableLiveData<User>()


    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun loginClicked(email: String, password: String) {
        Timber.d("Login clicked $email $password")
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = logInUserUseCase(email, password)
            when (result) {
                is LogInUserUseCase.Result.Success -> {
                    isLoading.postValue(false)
                    user.postValue(result.data)
                }
                is LogInUserUseCase.Result.Failure -> {

                    isLoading.postValue(false)
                }
            }
        }
    }
}