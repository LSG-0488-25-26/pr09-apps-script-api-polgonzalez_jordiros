package com.example.appscriptapi.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun LoginView(modifier: Modifier, viewModel: ViewModel, navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensageError by remember { mutableStateOf("") }

    var colorPrincipal = Color(0xFF9000FF)

    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Lista de animes",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 80.dp),
                color = colorPrincipal
            )
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
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
                    /* FUNCION DE VIEWMODEL PARA LOGIN */
                    // INTRODUCIR LOS PARAMETROS NOMBRE Y CONTRASEÑA
                    // SI LAS CREDENCIALES SON CORRECTAS NAVEGAR A ANIMELISTVIEW
                    // SI SE PRODUCEN ERRORES MODIFICA LA VARIABLE MENSAGEERROR

                    navController.navigate(Routes.AnimeList.route)
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrincipal,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Login",
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                onClick = { navController.navigate(Routes.Register.route) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorPrincipal
                )
            ) {
                Text(
                    text = "Signin",
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier = Modifier.padding(150.dp))
        }
    }
}