package com.example.fitquality.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitquality.domain.validation.validateEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Estado de la UI para login
data class AuthUiState(
    val email: String = "",
    val pass: String = "",
    val emailError: String? = null,
    val passError: String? = null,
    val isSubmitting: Boolean = false,
    val canSubmit: Boolean = false,
    val success: Boolean = false,
    val errorMsg: String? = null
)

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    // Funciones de validación de los campos
    fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(email = value, emailError = validateEmail(value))
        recomputeCanSubmit()
    }

    fun onPassChange(value: String) {
        _uiState.value = _uiState.value.copy(pass = value)
        recomputeCanSubmit()
    }

    private fun recomputeCanSubmit() {
        val canSubmit = _uiState.value.emailError == null && _uiState.value.pass.isNotBlank()
        _uiState.value = _uiState.value.copy(canSubmit = canSubmit)
    }

    fun submitLogin() {
        val state = _uiState.value
        if (!state.canSubmit || state.isSubmitting) return
        viewModelScope.launch {
            _uiState.value = state.copy(isSubmitting = true, errorMsg = null, success = false)
            // Simula el login, puede integrarse con API o base de datos
            kotlinx.coroutines.delay(1000)
            if (state.email == "test@example.com" && state.pass == "password123") {
                _uiState.value = state.copy(isSubmitting = false, success = true)
            } else {
                _uiState.value = state.copy(isSubmitting = false, errorMsg = "Credenciales inválidas")
            }
        }
    }
}