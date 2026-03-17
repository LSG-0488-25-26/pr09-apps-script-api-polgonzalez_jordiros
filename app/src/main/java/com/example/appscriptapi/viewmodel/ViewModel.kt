package com.example.appscriptapi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appscriptapi.model.Anime
import com.example.appscriptapi.model.GenerosAnime
import com.example.appscriptapi.model.TipoAnime

class ViewModel: ViewModel() {
    /*
    **********************
    * FUNCION PARA LOGIN *
    **********************
     */

    /*
    ***********************
    * FUNCION PARA SIGNIN *
    ***********************
     */

    /*
    **************************************
    * FUNCION PARA OBTENER ANIMES DE API *
    **************************************
     */

    // LISTA TEMPORAL DE PRUEBAS
    val animeList: List<Anime> = mutableListOf(
        Anime (32281, "Kimi no Na wa.", listOf(GenerosAnime.Drama, GenerosAnime.Romance, GenerosAnime.School, GenerosAnime.Supernatural), TipoAnime.Movie, "1", 9.37, 200630),
        Anime (5114, "Fullmetal Alchemist: Brotherhood",	listOf(GenerosAnime.Action, GenerosAnime.Adventure, GenerosAnime.Drama, GenerosAnime.Fantasy, GenerosAnime.Magic, GenerosAnime.Military, GenerosAnime.Shounen), TipoAnime.TV, "64", 9.26, 793665),
        Anime (28977, "Gintama", listOf(GenerosAnime.Action, GenerosAnime.Comedy, GenerosAnime.Historical, GenerosAnime.Parody, GenerosAnime.Samurai, GenerosAnime.Sci_Fi, GenerosAnime.Shounen), TipoAnime.TV, "51", 9.25, 114262),
        Anime (9253, "Steins;Gate", listOf (GenerosAnime.Sci_Fi, GenerosAnime.Thriller), TipoAnime.TV, "24", 9.17, 673572),
        Anime (9969, "Gintama&#039;", listOf(GenerosAnime.Action, GenerosAnime.Comedy, GenerosAnime.Historical, GenerosAnime.Parody, GenerosAnime.Samurai, GenerosAnime.Sci_Fi, GenerosAnime.Shounen), TipoAnime.TV, "51", 9.16, 151266),
    )

    /*
    **************************************
    * FUNCION PARA REGISTRAR NUEVO ANIME *
    **************************************
     */
}