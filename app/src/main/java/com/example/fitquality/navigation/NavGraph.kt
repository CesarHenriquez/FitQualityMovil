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


    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // HOME
        composable(route = "home") {
            HomeScreen(
                onGoLogin = { navController.navigate("login") },
                onGoRegister = { navController.navigate("register") }
            )
        }

        // LOGIN
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onGoRegister = {
                    navController.navigate("register") {
                        launchSingleTop = true
                    }
                }
                // ❌ 'repo = authRepo' ELIMINADO
            )
        }

        // REGISTER
        composable(route = "register") {
            RegisterScreen(
                onRegisteredSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onGoLogin = {
                    navController.navigate("login") {
                        launchSingleTop = true
                    }
                }
                // ❌ 'repo = authRepo' ELIMINADO
            )
        }
    }
}