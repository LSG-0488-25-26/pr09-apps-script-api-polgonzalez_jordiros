package com.example.appscriptapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appscriptapi.model.Review
import com.example.appscriptapi.viewmodel.ViewModel

@Composable
fun ReviewFormView(modifier: Modifier, viewModel: ViewModel, navController: NavController) {
    var comentario by remember { mutableStateOf("") }

    var colorPrincipal = Color(0xFF9000FF)

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 50.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = comentario,
            onValueChange = { comentario = it },
            label = { Text("Comentario") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val newReview = Review(
                    contenido = comentario
                )

                viewModel.addReview(newReview) {
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorPrincipal,
                contentColor = Color.White
            )
        ) {
            Text(text = "Comentar")
        }
    }
}