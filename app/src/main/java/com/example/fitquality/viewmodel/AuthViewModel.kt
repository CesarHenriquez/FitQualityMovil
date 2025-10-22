package com.example.fitquality.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fitquality.domain.validation.Validators // 👈 Nuevo import
import com.example.fitquality.repository.AuthRepository
import com.example.fitquality.repository.RepoHolder

/**
 * Resultado para las validaciones de registro.
 * Usamos null para indicar que el campo es válido.
 */
data class RegisterValidation(
    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val passwordError: String? = null,
    val confirmError: String? = null,
    val generalError: String? = null // Para errores como 'Email ya registrado' o 'Campos vacíos'
)

class AuthViewModel : ViewModel() {

    private val repo: AuthRepository = RepoHolder.repo

    /** Resultado claro para el login. */
    sealed class LoginResult {
        object Success : LoginResult()
        object UserNotFound : LoginResult()
        object WrongPassword : LoginResult()
    }

    /** Función de validación y registro */
    fun validateAndRegister(
        name: String,
        email: String,
        phone: String,
        password: String,
        confirm: String
    ): RegisterValidation {

        // Usamos las funciones de validación del Dominio
        var nameError = Validators.validateName(name.trim())
        var emailError = Validators.validateEmail(email.trim())
        var phoneError = Validators.validatePhone(phone.trim())
        var passwordError = Validators.validatePassword(password)
        var confirmError: String? = null
        var generalError: String? = null

        // Validación de Contraseñas Coincidentes (Lógica de negocio del registro)
        if (confirm.isBlank() || confirm != password) {
            confirmError = "Las contraseñas no coinciden"
        }

        // Validación de campos vacíos generales (si todos están vacíos)
        val allEmpty = name.isBlank() && email.isBlank() && phone.isBlank() && password.isBlank() && confirm.isBlank()
        if (allEmpty) {
            generalError = "Por favor complete el formulario de registro"
        }

        // 3. Si hay errores de formato (incluyendo 'confirm') o general, los retornamos
        if (nameError != null || emailError != null || phoneError != null || passwordError != null || confirmError != null || generalError != null) {
            return RegisterValidation(nameError, emailError, phoneError, passwordError, confirmError, generalError)
        }

        // 4. Si es válido, intentamos el registro (Validación de negocio: unicidad de email)
        if (repo.emailExists(email)) {
            // Error de negocio: Email duplicado
            return RegisterValidation(emailError = "El email ya está registrado")
        }

        // 5. Registro exitoso
        repo.saveUser(AuthRepository.User(name = name, email = email, phone = phone, password = password))
        return RegisterValidation() // Éxito: retorna un objeto sin errores
    }

    /** Verifica credenciales contra el repositorio persistente. */
    fun login(email: String, password: String): LoginResult {
        // Validación simple de campos no vacíos en Login
        if (email.isBlank() || password.isBlank()) {
            return LoginResult.WrongPassword // Mensaje de "Credenciales incorrectas" cubre campos vacíos en login
        }

        val u = repo.getUserByEmail(email) ?: return LoginResult.UserNotFound
        return if (u.password == password) LoginResult.Success else LoginResult.WrongPassword
    }
}