package com.example.fitquality.navigation

// Clase sellada para definir las rutas de la aplicación
sealed class Route(val path: String) {
    // Ruta para la pantalla principal (Home)
    object Home : Route("home")

    // Ruta para la pantalla de Login
    object Login : Route("login")

    // Ruta para la pantalla de Registro
    object Register : Route("register")
}