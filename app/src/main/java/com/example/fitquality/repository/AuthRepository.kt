package com.example.fitquality.repository

import android.content.Context

/**
 * Repositorio súper simple con persistencia usando SharedPreferences.
 * Guarda usuarios por email con la clave "user_<email>".
 */
class AuthRepository(context: Context) {

    private val prefs = context.getSharedPreferences("auth_repo", Context.MODE_PRIVATE)

    data class User(
        val name: String,
        val email: String,
        val phone: String,
        val password: String
    )

    /** Devuelve true si ya existe un usuario con ese email. */
    fun emailExists(email: String): Boolean {
        return prefs.contains(key(email))
    }

    /** Guarda/actualiza un usuario. */
    fun saveUser(user: User) {
        val packed = listOf(user.name, user.phone, user.password).joinToString("|")
        prefs.edit().putString(key(user.email), packed).apply()
    }

    /** Obtiene un usuario por email, o null si no existe. */
    fun getUserByEmail(email: String): User? {
        val packed = prefs.getString(key(email), null) ?: return null
        // Formato: name|phone|password
        val parts = packed.split("|")
        if (parts.size < 3) return null
        val (name, phone, password) = parts
        return User(name = name, email = email, phone = phone, password = password)
    }

    private fun key(email: String) = "user_$email"
}