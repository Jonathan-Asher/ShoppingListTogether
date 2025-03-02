package com.example.shoppinglisttogether.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglisttogether.data.LoginDataSource
import com.example.shoppinglisttogether.data.LoginRepository

/**
 * ViewModel provider factory to instantiate RegisterViewModel.
 * Required given RegisterViewModel has a non-empty constructor
 */
class RegisterViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 