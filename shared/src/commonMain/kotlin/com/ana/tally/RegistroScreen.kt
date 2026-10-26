package com.ana.tally

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistroScreen(onVolver: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var repetir by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("Colombia") }

    Box(
        modifier = Modifier.fillMaxSize().background(FondoTally),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 420.dp)
                .verticalScroll(rememberScrollState())
                .safeContentPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Flecha atrás, pegada a la izquierda
            Box(Modifier.fillMaxWidth()) {
                IconButton(onClick = onVolver, modifier = Modifier.align(Alignment.CenterStart)) {
                    Text("‹", fontSize = 36.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
            LogoTally()
            Spacer(Modifier.height(24.dp))
            Text("Completa los campos para registrarte", fontSize = 16.sp)
            Spacer(Modifier.height(24.dp))

            CampoTexto(nombre, { nombre = it }, "Nombres y apellidos", "Nombre Generico")
            Spacer(Modifier.height(16.dp))
            CampoTexto(correo, { correo = it }, "Correo electrónico", "ejemplo@ejemplo.com")
            Spacer(Modifier.height(16.dp))
            CampoContrasena(contrasena, { contrasena = it }, "Contraseña")
            Spacer(Modifier.height(16.dp))
            CampoContrasena(repetir, { repetir = it }, "Repetir contraseña")
            Spacer(Modifier.height(16.dp))
            CampoPais(pais) { pais = it }
            Spacer(Modifier.height(24.dp))

            BotonPrincipal("Registrarse") { }   // o "Iniciar Sesion", como en el diseño
            Spacer(Modifier.height(24.dp))

            TextoConEnlace("Ya posees una cuenta. Inicia sesión", "Aquí", onVolver)
            Spacer(Modifier.height(24.dp))
        }
    }
}