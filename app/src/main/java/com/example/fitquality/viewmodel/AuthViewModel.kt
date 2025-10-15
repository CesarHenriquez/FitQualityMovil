package com.example.fitquality.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitquality.domain.validation.validateEmail
import com.example.fitquality.domain.validation.validatePassword
import com.example.fitquality.domain.validation.validateUsername
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * AuthUiState concentra TODO el estado de la pantalla:
 * - campos
 * - errores de validación
 * - banderas de "cargando"
 * - resultado (logueado/registrado)
 */
data class AuthUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false, // para navegación a Home
    val message: String? = null // feedback simple
)

/**

 * Por ahora simulamos la "llamada remota" con delay().
 */
class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(name = value, nameError = null, message = null)
    }

    fun onEmailChange(value: String) {
        _uiState.value = _uiState.value.copy(email = value, emailError = null, message = null)
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(password = value, passwordError = null, message = null)
    }

    /**
     * Valida los campos. Si hay error, actualiza el estado con los mensajes.
     * Devuelve true si todo OK.
     */
    private fun validateForLoginOrRegister(checkName: Boolean): Boolean {
        val s = _uiState.value
        val nameErr = if (checkName) validateUsername(s.name) else null
        val emailErr = validateEmail(s.email)
        val passErr = validatePassword(s.password)

        val hasError = (nameErr != null) || (emailErr != null) || (passErr != null)
        _uiState.value = s.copy(
            nameError = nameErr,
            emailError = emailErr,
            passwordError = passErr
        )
        return !hasError
    }

    /**
     * Simula un LOGIN
     */
    fun login() {
        if (!validateForLoginOrRegister(checkName = false)) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, message = null)
            delay(3000) // <-- loader de 3 segundos
            // "Simulación": si pass == "password123" damos OK, si no, error simple
            val success = _uiState.value.password == "password123"
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isLoggedIn = success,
                message = if (success) "Inicio de sesión correcto." else "Credenciales incorrectas."
            )
        }
    }

    /**
     * Simula un REGISTER
     */
    fun register() {
        if (!validateForLoginOrRegister(checkName = true)) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, message = null)
            delay(3000) // <-- loader de 3 segundos
            // "Simulación" exitosa siempre:
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isLoggedIn = true,
                message = "Registro completado."
            )
        }
    }

    fun logout() {
        _uiState.value = AuthUiState() // resetea todo
    }
}