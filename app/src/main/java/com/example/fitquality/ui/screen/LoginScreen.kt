package com.example.fitquality.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.fitquality.repository.AuthRepository
import com.example.fitquality.viewmodel.AuthViewModel
import com.example.fitquality.viewmodel.AuthViewModel.LoginResult


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onGoRegister: () -> Unit,
    // ❌ ARGUMENTO 'repo: AuthRepository' ELIMINADO
) {
    // ✅ CREACIÓN DEL VM: Ahora usa el constructor vacío, y el VM internamente usa RepoHolder
    val viewModel = remember { AuthViewModel() }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf<String?>(null) }
    var isSubmitting by remember { mutableStateOf(false) }

    fun submitLogin() {
        isSubmitting = true
        val result = viewModel.login(email.trim(), password)
        when (result) {
            is LoginResult.Success -> {
                errorMsg = null
                onLoginSuccess()
            }
            is LoginResult.UserNotFound -> {
                errorMsg = "El usuario no existe"
            }
            is LoginResult.WrongPassword -> {
                errorMsg = "Credenciales incorrectas"
            }
        }
        isSubmitting = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        errorMsg?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(8.dp))
        }

        Button(
            onClick = { submitLogin() },
            enabled = !isSubmitting,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSubmitting) "..." else "Login")
        }

        Spacer(Modifier.height(16.dp))

        OutlinedButton(
            onClick = onGoRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("No tienes cuenta? Regístrate aquí")
        }
    }
}