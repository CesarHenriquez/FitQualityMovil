package com.example.fitquality

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

import com.example.fitquality.navigation.NavGraph
import com.example.fitquality.ui.theme.FitQualityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    val navController = rememberNavController() // controlador de navegación
    FitQualityTheme {  // tema
        Surface(color = MaterialTheme.colorScheme.background) {
            NavGraph(navController = navController) // Enlazamiento de la navegación
        }
    }
}

