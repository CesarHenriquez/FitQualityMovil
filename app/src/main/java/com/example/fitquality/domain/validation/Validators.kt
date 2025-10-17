package com.example.fitquality.domain.validation

import android.util.Patterns

// Valida que el email no esté vacío y cumpla patrón de email
fun validateEmail(email: String): String? {
    if (email.isBlank()) return "El email es obligatorio"
    val ok = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    return if (!ok) "Formato de email inválido" else null
}

// Valida que el nombre contenga solo letras y espacios (sin números)
fun validateNameLettersOnly(name: String): String? {
    if (name.isBlank()) return "El nombre es obligatorio"
    val regex = Regex("^[A-Za-zÁÉÍÓÚÑáéíóúñ ]+$")
    return if (!regex.matches(name)) "Solo letras y espacios" else null
}

// Valida que el teléfono tenga solo dígitos y una longitud razonable
fun validatePhoneDigitsOnly(phone: String): String? {
    if (phone.isBlank()) return "El teléfono es obligatorio"
    if (!phone.all { it.isDigit() }) return "Solo números"
    if (phone.length !in 8..15) return "Debe tener entre 8 y 15 dígitos"
    return null
}

// Valida que la confirmación coincida con la contraseña
fun validateConfirm(pass: String, confirm: String): String? {
    if (confirm.isBlank()) return "Confirma tu contraseña"
    return if (pass != confirm) "Las contraseñas no coinciden" else null
}