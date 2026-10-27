package com.ana.tally

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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

// Etiqueta fija sobre el borde, como en Figma
@Composable
fun CampoConEtiqueta(etiqueta: String, campo: @Composable () -> Unit) {
    Box(Modifier.fillMaxWidth().padding(top = 8.dp)) {
        campo()
        Text(
            etiqueta,
            color = GrisTexto,
            fontSize = 12.sp,
            modifier = Modifier
                .offset(x = 12.dp, y = (-8).dp)
                .background(
                    // mitad de arriba color de la pantalla, mitad de abajo blanco como el campo
                    Brush.verticalGradient(
                        0f to FondoTally, 0.5f to FondoTally,
                        0.5f to Color.White, 1f to Color.White
                    )
                )
                .padding(horizontal = 4.dp)
        )
    }
}
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
    ejemplo: String = " "
) {
    CampoConEtiqueta(etiqueta) {
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            placeholder = { Text(ejemplo, color = GrisTexto) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = coloresCampo(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CampoContrasena(
    valor: String,
    onValorChange: (String) -> Unit,
    etiqueta: String
) {
    var visible by remember { mutableStateOf(false) }

    CampoConEtiqueta(etiqueta) {
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            placeholder = { Text("******", color = GrisTexto) },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = coloresCampo(),
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation('*'),
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
}

@Composable
fun CampoPais(pais: String, onPaisChange: (String) -> Unit) {
    val paises = listOf("Colombia", "España", "México", "Argentina", "Chile", "Perú")
    var abierto by remember { mutableStateOf(false) }

    CampoConEtiqueta("País") {
        Box {
            OutlinedTextField(
                value = pais,
                onValueChange = { },
                readOnly = true,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = coloresCampo(),
                trailingIcon = {
                    IconButton(onClick = { abierto = true }) {
                        Text("›", fontSize = 28.sp)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(expanded = abierto, onDismissRequest = { abierto = false }) {
                paises.forEach { p ->
                    DropdownMenuItem(
                        text = { Text(p) },
                        onClick = {
                            onPaisChange(p)
                            abierto = false
                        }
                    )
                }
            }
        }
    }
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
    Row {
        Text(
            texto,
            modifier = Modifier.alignByBaseline().weight(1f, fill = false)
        )
        Text(
            enlace,
            color = VerdeTally,
            softWrap = false,
            modifier = Modifier
                .alignByBaseline()
                .clickable(onClick = onClick)
                .padding(start = 4.dp)
        )
    }
}

