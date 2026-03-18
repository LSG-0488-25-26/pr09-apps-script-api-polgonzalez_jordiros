package com.example.appscriptapi.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appscriptapi.api.AnimeApiService
import com.example.appscriptapi.model.Anime
import com.example.appscriptapi.model.GenerosAnime
import com.example.appscriptapi.model.TipoAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// ViewModel.kt
class ViewModel: ViewModel() {
    // 1. Cliente para controlar timeouts y redirecciones
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    // 2. Configuración de Retrofit con la URL base
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://script.google.com/macros/s/AKfycbwAxkx8WUQfpoxTiKpracrlHvUyHqlpCmyBdCLIf0sAGde7YqLeqCUmPS9ps75o5hoq/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(AnimeApiService::class.java)

    // Estados reactivos para la UI
    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    // -- AUTENTICACIÓN CON SHAREDPREFERENCES --
    fun login(user: String, pass: String, context: Context, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val registeredUser = sharedPref.getString("username", null)
            val registeredPass = sharedPref.getString("password", null)

            if (user.isEmpty() || pass.isEmpty()) {
                errorMessage = "Campos vacíos"
            } else if (user == registeredUser && pass == registeredPass) {
                sharedPref.edit().putBoolean("isLoggedIn", true).apply()
                errorMessage = ""
                onSuccess()
            } else {
                errorMessage = "Usuario o contraseña incorrectos"
            }
        }
    }

    fun logout(context: Context, onSuccess: () -> Unit) {
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            .edit().putBoolean("isLoggedIn", false).apply()
        onSuccess()
    }

    fun signin(user: String, pass: String, context: Context, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val editor = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE).edit()
            editor.putString("username", user)
            editor.putString("password", pass)
            editor.putBoolean("isLoggedIn", true)
            editor.apply()
            errorMessage = ""
            onSuccess()
        }
    }

    // -- LLAMADAS A LA API --
    fun fetchAnimes() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = ""
            try {
                val response = apiService.getAnimes()
                if (response.isSuccessful && response.body() != null) {
                    _animeList.value = response.body()!!.data
                } else {
                    errorMessage = "Error API: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error de red. Revisa tu conexión."
            } finally {
                isLoading = false
            }
        }
    }

    fun addAnime(newAnime: Anime, onSuccess: () -> Unit) {
        viewModelScope.launch {
            errorMessage = ""
            try {
                val response = apiService.postAnime(anime = newAnime)
                if (response.isSuccessful) {
                    fetchAnimes() // Recargamos la lista tras añadir
                    onSuccess()
                } else {
                    errorMessage = "Error al crear el anime"
                }
            } catch (e: Exception) {
                errorMessage = "No se pudo conectar"
            }
        }
    }
}