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
import com.example.fitquality.viewmodel.AuthViewModel
import com.example.fitquality.viewmodel.RegisterValidation

@Composable
fun RegisterScreen(
    onRegisteredSuccess: () -> Unit,
    onGoLogin: () -> Unit,
) {
    val viewModel = remember { AuthViewModel() }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    // Usa un único estado para almacenar los errores de validación
    var validationErrors by remember { mutableStateOf(RegisterValidation()) }


    fun submitRegister() {
        // 1. Llama a la nueva función de validación del ViewModel
        val result = viewModel.validateAndRegister(
            name = name.trim(),
            email = email.trim(),
            phone = phone.trim(),
            password = password,
            confirm = confirm
        )

        // 2. Almacena el resultado
        validationErrors = result

        // 3. Revisa si hay algún error
        if (result.nameError == null && result.emailError == null && result.phoneError == null && result.passwordError == null && result.confirmError == null && result.generalError == null) {
            onRegisteredSuccess() // Éxito en el registro
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registro", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        // ------------------ CAMPO NOMBRE ------------------
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            isError = validationErrors.nameError != null,
            supportingText = { validationErrors.nameError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // ------------------ CAMPO EMAIL ------------------
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            isError = validationErrors.emailError != null,
            supportingText = { validationErrors.emailError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // ------------------ CAMPO TELÉFONO ------------------
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            isError = validationErrors.phoneError != null,
            supportingText = { validationErrors.phoneError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // ------------------ CAMPO CONTRASEÑA ------------------
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            isError = validationErrors.passwordError != null,
            supportingText = { validationErrors.passwordError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        // ------------------ CAMPO CONFIRMAR ------------------
        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            isError = validationErrors.confirmError != null,
            supportingText = { validationErrors.confirmError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        // Mensaje global (para campos vacíos generales)
        validationErrors.generalError?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { submitRegister() },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Registrar") }

        Spacer(Modifier.height(16.dp))
        OutlinedButton(
            onClick = onGoLogin,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Ya tienes cuenta? Inicia sesión aquí") }
    }
}