package com.example.shoppinglisttogether.ui.register

import com.example.shoppinglisttogether.ui.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
) 