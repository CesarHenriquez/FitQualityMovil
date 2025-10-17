package com.example.fitquality.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

data class DrawerItem(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun AppDrawer(
    currentRoute: String?,
    items: List<DrawerItem>,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier = modifier) {
        items.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.label) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                selected = false,  // Esto lo puedes actualizar para resaltar el item actual
                onClick = item.onClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun defaultDrawerItems(
    onHome: () -> Unit,
    onLogin: () -> Unit,
    onRegister: () -> Unit
): List<DrawerItem> = listOf(
    DrawerItem("Home", Icons.Filled.Home, onHome),
    DrawerItem("Login", Icons.Filled.AccountCircle, onLogin),
    DrawerItem("Register", Icons.Filled.Person, onRegister)
)

@Preview(showBackground = true)
@Composable
fun AppDrawerPreview() {
    AppDrawer(
        currentRoute = "home",
        items = defaultDrawerItems(onHome = {}, onLogin = {}, onRegister = {})
    )
}