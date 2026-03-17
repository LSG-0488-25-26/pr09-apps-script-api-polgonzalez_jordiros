package com.example.appscriptapi.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appscriptapi.model.Anime
import com.example.appscriptapi.viewmodel.ViewModel

@Composable
fun AnimeItemView(anime: Anime, viewModel: ViewModel) {
    var colorPrincipal = Color(0xFF9000FF)
    var colorValoracion = Color(0xFFFF8400)
    var colorFondo = Color(0xFF242424)

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
    ) {
        Column(
            modifier = Modifier.background(colorFondo).padding(10.dp).fillMaxSize()
        ) {
            Text(
                text = anime.nombre,
                fontSize = 25.sp,
                color = colorPrincipal
            )
            Text(
                text = anime.generos.joinToString(", "),
                fontSize = 15.sp,
                color = Color.White
            )
            Text(
                text = "Tipo: " + anime.tipo,
                fontSize = 15.sp,
                color = Color.White
            )
            Text(
                text = "Miembros: " + anime.miembros,
                fontSize = 15.sp,
                color = Color.White
            )
            Text(
                text = "Valoración: " + anime.valoracion.toString(),
                fontSize = 20.sp,
                color = colorValoracion
            )
        }
    }
}