package com.ana.tally

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import tally.shared.generated.resources.*

// Colores sacados del diseño de Figma
val FondoTally = Color(0xFFF5F9FF)
val VerdeTally = Color(0xFF16924A)
val GrisBorde = Color(0xFFC4C8CE)
val GrisTexto = Color(0xFF8E949A)

@Composable
fun coloresCampo() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = VerdeTally,
    unfocusedBorderColor = GrisBorde,
    focusedLabelColor = VerdeTally,
    unfocusedLabelColor = GrisTexto,
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    cursorColor = VerdeTally
)

@Composable
fun LogoTally() {
    Image(
        painter = painterResource(Res.drawable.logo_tally),
        contentDescription = "Logo de Tally",
        modifier = Modifier.width(260.dp)
    )
}

@Composable
fun CampoTexto(
    valor: String,
    onValorChange: (String) -> Unit,
    etiqueta: String,
    ejemplo: String = ""
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(etiqueta) },
        placeholder = { Text(ejemplo, color = GrisTexto) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = coloresCampo(),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CampoContrasena(
    valor: String,
    onValorChange: (String) -> Unit,
    etiqueta: String
) {
    var visible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(etiqueta) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = coloresCampo(),
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Image(
                    painter = painterResource(Res.drawable.ic_eye),
                    contentDescription = if (visible) "Ocultar contraseña" else "Mostrar contraseña",
                    alpha = 0.4f,
                    modifier = Modifier.size(26.dp)
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun BotonPrincipal(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = VerdeTally),
        modifier = Modifier.fillMaxWidth().height(54.dp)
    ) {
        Text(texto, fontSize = 18.sp)
    }
}

@Composable
fun TextoConEnlace(texto: String, enlace: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(texto)
        Text(
            enlace,
            color = VerdeTally,
            modifier = Modifier.clickable(onClick = onClick).padding(4.dp)
        )
    }
}