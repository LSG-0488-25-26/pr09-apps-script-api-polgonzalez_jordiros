package com.example.appscriptapi.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appscriptapi.nav.Routes
import com.example.appscriptapi.viewmodel.ViewModel

@Composable
fun RegisterView(modifier: Modifier, viewModel: ViewModel, navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var repetirContrasena by remember { mutableStateOf("") }
    var mensageError by remember { mutableStateOf("") }
    var colorPrincipal = Color(0xFF9000FF)

    Surface (
        modifier = Modifier.fillMaxSize(),
    ) {
        Column (
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ventana registro",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 80.dp),
                color = colorPrincipal
            )
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text(text = "Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                value = repetirContrasena,
                onValueChange = { repetirContrasena = it },
                label = { Text(text = "Repetir contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = mensageError,
                color = Color.Red
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                onClick = {
                    /* FUNCION DE VIEWMODEL PARA SIGNIN */
                    // INTRODUCIR LOS PARAMETROS NOMBRE, CONTRASEÑA Y REPETIR CONTRASEÑA
                    // SI LAS CREDENCIALES SON CORRECTAS NAVEGAR A ANIMELISTVIEW
                    // SI SE PRODUCEN ERRORES MODIFICA LA VARIABLE MENSAGEERROR

                    navController.navigate(Routes.AnimeList.route)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrincipal,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Signin",
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                onClick = { navController.navigate(Routes.Login.route) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorPrincipal
                )
            ) {
                Text(
                    text = "Atras",
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}