package com.ana.tally

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

enum class Pantalla { LOGIN, REGISTRO }

@Composable
@Preview
fun App() {
    MaterialTheme {
        var pantalla by remember { mutableStateOf(Pantalla.LOGIN) }

        when (pantalla) {
            Pantalla.LOGIN -> LoginScreen(
                onIrARegistro = { pantalla = Pantalla.REGISTRO }
            )
            Pantalla.REGISTRO -> RegistroScreen(
                onVolver = { pantalla = Pantalla.LOGIN }
            )
        }
    }
}
