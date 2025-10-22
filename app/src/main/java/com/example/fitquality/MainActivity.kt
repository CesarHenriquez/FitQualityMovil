package com.example.fitquality

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

import com.example.fitquality.navigation.NavGraph
import com.example.fitquality.repository.RepoHolder
import com.example.fitquality.ui.theme.FitQualityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ⚠️ Inicializa el repositorio persistente UNA sola vez antes de pintar UI
        RepoHolder.init(applicationContext)

        setContent {
            FitQualityTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}