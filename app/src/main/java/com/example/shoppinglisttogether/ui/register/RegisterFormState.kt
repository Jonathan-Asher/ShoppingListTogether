package com.example.shoppinglisttogether.ui.register

import androidx.annotation.StringRes

/**
 * Data validation state of the registration form.
 */
data class RegisterFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val confirmPasswordError: Int? = null,
    val isDataValid: Boolean = false
) 