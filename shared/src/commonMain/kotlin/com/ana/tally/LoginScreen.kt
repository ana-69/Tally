package com.ana.tally

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import tally.shared.generated.resources.*

@Composable
fun LoginScreen(onIrARegistro: () -> Unit) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().background(FondoTally),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 420.dp)          // en escritorio no se estira de lado a lado
                .verticalScroll(rememberScrollState())
                .safeContentPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(60.dp))
            LogoTally()
            Spacer(Modifier.height(32.dp))
            Text("Ingresa ahora mismo", fontSize = 16.sp)
            Spacer(Modifier.height(32.dp))

            CampoTexto(correo, { correo = it }, "Correo electrónico", "ejemplo@ejemplo.com")
            Spacer(Modifier.height(16.dp))
            CampoContrasena(contrasena, { contrasena = it }, "Contraseña")
            Spacer(Modifier.height(24.dp))

            BotonPrincipal("Iniciar Sesion") { }
            Spacer(Modifier.height(12.dp))

            TextButton(onClick = { }) {
                Text("Olvido su contraseña", color = GrisTexto, fontSize = 16.sp)
            }
            Spacer(Modifier.height(12.dp))

            // Línea – o – línea
            Row(verticalAlignment = Alignment.CenterVertically) {
                HorizontalDivider(Modifier.weight(1f), color = GrisBorde)
                Text("o", modifier = Modifier.padding(horizontal = 12.dp), color = GrisTexto)
                HorizontalDivider(Modifier.weight(1f), color = GrisBorde)
            }
            Spacer(Modifier.height(20.dp))

            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, GrisBorde),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth().height(54.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_google),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text("Iniciar con Google", color = Color(0xFF5F6368), fontSize = 16.sp)
            }
            Spacer(Modifier.height(40.dp))

            TextoConEnlace("Si no posees una cuenta. Crea una", "Ahora", onIrARegistro)
            Spacer(Modifier.height(24.dp))
        }
    }
}