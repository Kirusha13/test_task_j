package com.example.coursesapp.feature.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isButtonEnabled: Boolean = false
)
