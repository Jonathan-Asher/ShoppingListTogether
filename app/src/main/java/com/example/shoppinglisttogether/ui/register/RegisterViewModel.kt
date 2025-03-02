package com.example.shoppinglisttogether.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.shoppinglisttogether.data.LoginRepository
import com.example.shoppinglisttogether.data.Result

import com.example.shoppinglisttogether.R
import com.example.shoppinglisttogether.ui.login.LoggedInUserView

class RegisterViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _registerResult.value = RegisterResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _registerResult.value = RegisterResult(error = R.string.registration_failed)
        }
    }

    fun registerDataChanged(username: String, password: String, confirmPassword: String) {
        if (!isUserNameValid(username)) {
            _registerForm.value = RegisterFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else if (password != confirmPassword) {
            _registerForm.value = RegisterFormState(confirmPasswordError = R.string.passwords_dont_match)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
} 