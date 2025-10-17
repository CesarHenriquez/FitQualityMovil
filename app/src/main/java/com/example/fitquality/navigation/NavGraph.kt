package com.example.fitquality.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitquality.ui.screen.HomeScreen

import com.example.fitquality.ui.screen.LoginScreen
import com.example.fitquality.ui.screen.RegisterScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(
                onGoLogin = {
                    // Acción para navegar a la pantalla de login
                    navController.navigate("login")
                },
                onGoRegister = {
                    // Acción para ir a la pantalla de registro
                    navController.navigate("register")
                }
            )
        }
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {
                    // Acción al hacer login exitoso (navegar al home)
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // Elimina la pantalla de login del stack
                    }
                },
                onGoRegister = {
                    // Acción para ir a la pantalla de registro desde login
                    navController.navigate("register")
                }
            )
        }
        composable(route = "register") {
            RegisterScreen(
                onRegisteredSuccess = {
                    // Acción después de registro exitoso, navegamos a login
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true } // Elimina la pantalla de registro
                    }
                },
                onGoLogin = {
                    // Acción para ir a login desde la pantalla de registro
                    navController.navigate("login")
                }
            )
        }
    }
}