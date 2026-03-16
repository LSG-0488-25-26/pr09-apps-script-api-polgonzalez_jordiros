package com.example.appscriptapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appscriptapi.nav.Routes
import com.example.appscriptapi.viewmodel.ViewModel

@Composable
fun AnimeListView(modifier: Modifier, viewModel: ViewModel, navController: NavController) {
    Column (
        modifier = Modifier.padding(20.dp)
    ) {
        Button(
            onClick = { navController.navigate(Routes.Login.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
        Button(
            onClick = { navController.navigate(Routes.PostForm.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nuevo anime")
        }
    }
}