package com.example.fitquality.domain.validation

// validador de email básico
private val EMAIL_REGEX =
    Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

/**
 * Valida el nombre de usuario:
 * - No vacío
 * - Longitud mínima (4)
 */
fun validateUsername(input: String): String? {
    val trimmed = input.trim()
    if (trimmed.isEmpty()) return "El nombre no puede estar vacío."
    if (trimmed.length < 4) return "El nombre debe tener al menos 4 caracteres."
    return null // null == sin error
}

/**
 * Valida email en formato simple.
 */
fun validateEmail(input: String): String? {
    val trimmed = input.trim()
    if (trimmed.isEmpty()) return "El correo no puede estar vacío."
    if (!EMAIL_REGEX.matches(trimmed)) return "Formato de correo inválido."
    return null
}

/**
 * Valida contraseña:
 * - Mínimo 8 caracteres
 */
fun validatePassword(input: String): String? {
    if (input.isEmpty()) return "La contraseña no puede estar vacía."
    if (input.length < 8) return "La contraseña debe tener al menos 8 caracteres."
    return null
}
