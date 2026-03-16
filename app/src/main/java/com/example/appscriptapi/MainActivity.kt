package com.example.appscriptapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appscriptapi.nav.Routes
import com.example.appscriptapi.ui.theme.AppScriptAPITheme
import com.example.appscriptapi.view.AnimeListView
import com.example.appscriptapi.view.LoginView
import com.example.appscriptapi.view.PostFormView
import com.example.appscriptapi.view.RegisterView
import com.example.appscriptapi.viewmodel.ViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    val viewModel: ViewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppScriptAPITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Login.route
                    ) {
                        composable(Routes.Login.route) { LoginView(modifier = Modifier.padding(innerPadding), viewModel, navController) }
                        composable(Routes.Register.route) { RegisterView(modifier = Modifier.padding(innerPadding), viewModel, navController) }
                        composable(Routes.AnimeList.route) { AnimeListView(modifier = Modifier.padding(innerPadding), viewModel, navController) }
                        composable(Routes.PostForm.route) { PostFormView(modifier = Modifier.padding(innerPadding), viewModel, navController) }
                    }
                }
            }
        }
    }
}
