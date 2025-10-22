package com.example.fitquality.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitquality.ui.components.AppTopBar

/**
 * Pantalla de inicio sencilla. Recibe lambdas para navegar
 */
@Composable
fun HomeScreen(
    onGoLogin: () -> Unit,
    onGoRegister: () -> Unit,
    onOpenDrawer: () -> Unit = {},
    onGoHome: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            AppTopBar(
                onOpenDrawer = onOpenDrawer,
                onHome = onGoHome,
                onLogin = onGoLogin,
                onRegister = onGoRegister
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenido a FitQuality")
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = onGoLogin,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Iniciar sesión") }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onGoRegister,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Crear cuenta") }
        }
    }
}