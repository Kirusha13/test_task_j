package com.example.coursesapp.feature.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEmailChanged(email: String) {
        val filtered = email.filter { !it.isCyrillicChar() }
        _state.update {
            it.copy(email = filtered, isButtonEnabled = isValid(filtered, it.password))
        }
    }

    fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(password = password, isButtonEnabled = isValid(it.email, password))
        }
    }

    private fun isValid(email: String, password: String): Boolean {
        val emailPattern = Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
        return email.matches(emailPattern) && password.isNotEmpty()
    }

    private fun Char.isCyrillicChar(): Boolean {
        return this in '\u0400'..'\u04FF'
    }
}
