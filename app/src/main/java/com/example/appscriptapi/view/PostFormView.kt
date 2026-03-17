package com.example.appscriptapi.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appscriptapi.model.TipoAnime
import com.example.appscriptapi.nav.Routes
import com.example.appscriptapi.viewmodel.ViewModel

@Composable
fun PostFormView(modifier: Modifier, viewModel: ViewModel, navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var miembros by remember { mutableStateOf("") }
    var valoracion by remember { mutableStateOf("") }
    var mensageError by remember { mutableStateOf("") }

    var tipoSeleccionado by remember { mutableStateOf<TipoAnime?>(null) }
    var menuExpandido by remember { mutableStateOf(false) }

    var colorPrincipal = Color(0xFF9000FF)

    Column (
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 50.dp).fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(vertical = 50.dp))
        Text(
            text = "Ingresar nuevo anime",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 60.dp),
            color = colorPrincipal
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Button(
                onClick = { menuExpandido = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrincipal,
                    contentColor = Color.White
                )
            ) {
                Text(tipoSeleccionado?.name ?: "Seleccionar tipo")
            }

            DropdownMenu(
                expanded = menuExpandido,
                onDismissRequest = { menuExpandido = false }
            ) {
                TipoAnime.values().forEach { tipo ->
                    DropdownMenuItem(
                        text = { Text(tipo.name) },
                        onClick = {
                            tipoSeleccionado = tipo
                            menuExpandido = false
                        }
                    )
                }

                DropdownMenuItem(
                    text = { Text("Ninguno") },
                    onClick = {
                        tipoSeleccionado = null
                        menuExpandido = false
                    }
                )
            }
        }

        OutlinedTextField(
            value = miembros,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    miembros = input
                }
            },
            label = { Text("Miembros") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = valoracion,
            onValueChange = { input ->
                if (input.matches(Regex("^\\d{0,2}(\\.\\d{0,2})?\$"))) {

                    val numero = input.toDoubleOrNull()

                    if (numero == null || numero in 0.0..10.0) {
                        valoracion = input
                    }
                }
            },
            label = { Text("0.00 (0 - 10)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = mensageError,
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    /* APLICAR FUNCION PARA INGRESAR NUEVO ANOME */
                    // ENVIAR PARAMETROS DE: NOMBRE, LISTA DE GENEROS, TIPO, MIEMBROS Y VALORACION
                    // SI HAY ALGUN ERROR CAMBIAR EL VALOR DE MENSAGEERROR
                    navController.navigate(Routes.AnimeList.route)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrincipal,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Guardar",
                    fontSize = 20.sp,
                )
            }
            Button(
                onClick = { navController.navigate(Routes.AnimeList.route) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorPrincipal
                )
            ) {
                Text(
                    text = "Cancelar",
                    fontSize = 20.sp,
                )
            }
        }
    }
}