package com.example.appscriptapi.model

import androidx.room.PrimaryKey

data class Anime (
    @PrimaryKey var id: Int,
    var nombre: String,
    var generos: List<String>,
    var tipo: TipoAnime?,
    var episodios: Int,
    var valoracion: Double,
    var miembros: Int
)

enum class TipoAnime {
    Movie,
    TV,
    OVA,
    Special,
    Music,
    ONA
}