package com.example.appscriptapi.view

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appscriptapi.nav.Routes
import com.example.appscriptapi.viewmodel.ViewModel

@Composable
fun AnimeListView(modifier: Modifier, viewModel: ViewModel, navController: NavController) {
    val context = LocalContext.current
    var colorPrincipal = Color(0xFF9000FF)

    // Disparamos la petición a la API al cargar la vista
    LaunchedEffect(Unit) {
        viewModel.fetchAnimes()
    }

    // Observamos la lista reactiva del ViewModel
    val animes by viewModel.animeList.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 50.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrincipal,
                    contentColor = Color.White
                ),
                onClick = {
                    viewModel.logout(context) {
                        navController.navigate(Routes.Login.route) { popUpTo(0) }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "Logout",
                    fontSize = 20.sp,
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorPrincipal,
                    contentColor = Color.White
                ),
                onClick = { navController.navigate(Routes.PostForm.route) },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "Nuevo anime",
                    fontSize = 20.sp,
                )
            }
        }

        Spacer(modifier = Modifier.padding(bottom = 10.dp))

        // Indicadores de Carga o Error
        if (viewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (viewModel.errorMessage.isNotEmpty()) {
            Text(
                text = viewModel.errorMessage,
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
        ) {
            items(animes.size) { index ->
                AnimeItemView(
                    anime = animes[index],
                    navController = navController
                )
            }
        }
    }
}