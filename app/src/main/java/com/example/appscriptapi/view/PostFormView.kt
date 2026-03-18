package com.example.appscriptapi.view

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.appscriptapi.model.Anime
import com.example.appscriptapi.model.GenerosAnime
import com.example.appscriptapi.model.TipoAnime
import com.example.appscriptapi.nav.Routes
import com.example.appscriptapi.viewmodel.ViewModel

@Composable
fun PostFormView(modifier: Modifier, viewModel: ViewModel, navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var episodios by remember { mutableStateOf("") }
    var miembros by remember { mutableStateOf("") }
    var valoracion by remember { mutableStateOf("") }

    var generoSeleccionado by remember { mutableStateOf<GenerosAnime?>(null) }
    val generosSeleccionados = remember { mutableStateListOf<GenerosAnime>() }
    var menuGenerosExpandido by remember { mutableStateOf(false) }

    var tipoSeleccionado by remember { mutableStateOf<TipoAnime?>(null) }
    var menuTipoExpandido by remember { mutableStateOf(false) }

    var colorPrincipal = Color(0xFF9000FF)

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 50.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = "Ingresar nuevo anime",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp), // Ajustado el padding para que quepa bien
            color = colorPrincipal
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
            Button(
                onClick = { menuGenerosExpandido = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrincipal,
                    contentColor = Color.White
                )
            ) {
                Text(
                    generoSeleccionado?.name ?: "Seleccionar género"
                )
            }

            DropdownMenu(
                expanded = menuGenerosExpandido,
                onDismissRequest = { menuGenerosExpandido = false }
            ) {
                GenerosAnime.values().forEach { genero ->
                    DropdownMenuItem(
                        text = { Text(genero.name) },
                        onClick = {
                            generoSeleccionado = genero
                            menuGenerosExpandido = false
                            if (!generosSeleccionados.contains(genero)) {
                                generosSeleccionados.add(genero)
                                generoSeleccionado = null
                            }
                        }
                    )
                }
            }
        }
        Text(
            text = if (generosSeleccionados.isEmpty()) "No hay géneros seleccionados"
            else "Géneros: ${generosSeleccionados.joinToString { it.name }}",
        )
        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Button(
                onClick = { menuTipoExpandido = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrincipal,
                    contentColor = Color.White
                )
            ) {
                Text(tipoSeleccionado?.name ?: "Seleccionar tipo")
            }
            DropdownMenu(
                expanded = menuTipoExpandido,
                onDismissRequest = { menuTipoExpandido = false }
            ) {
                TipoAnime.values().forEach { tipo ->
                    DropdownMenuItem(
                        text = { Text(tipo.name) },
                        onClick = {
                            tipoSeleccionado = tipo
                            menuTipoExpandido = false
                        }
                    )
                }
                DropdownMenuItem(
                    text = { Text("Ninguno") },
                    onClick = {
                        tipoSeleccionado = null
                        menuTipoExpandido = false
                    }
                )
            }
        }
        OutlinedTextField(
            value = episodios,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    if (input.isNotEmpty() && input.toInt() == 0) {
                        episodios = "Unknown"
                    } else {
                        episodios = input
                    }
                }
            },
            label = { Text("Episodios") },
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

        // Muestra el error de ViewModel si lo hay
        if (viewModel.errorMessage.isNotEmpty()) {
            Text(
                text = viewModel.errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    // Creamos el objeto mapeando los Enum a String
                    val nuevoAnime = Anime(
                        nombre = nombre,
                        generos = generosSeleccionados.map { it.name },
                        tipo = tipoSeleccionado?.name,
                        episodios = if (episodios.isEmpty()) "Unknown" else episodios,
                        valoracion = if (valoracion.isEmpty()) "0.0" else valoracion,
                        miembros = if (miembros.isEmpty()) "0" else miembros
                    )

                    // Pasamos el nuevoAnime al ViewModel
                    viewModel.addAnime(nuevoAnime) {
                        navController.popBackStack() // Cierra la pantalla al tener éxito
                    }
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
                onClick = { navController.popBackStack() }, // Simplificado para volver atrás
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
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