package com.example.appscriptapi.model

import androidx.room.PrimaryKey

data class Anime (
    @PrimaryKey var id: Int,
    var nombre: String,
    var generos: List<GenerosAnime>,
    var tipo: TipoAnime?,
    var episodios: String,
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

enum class GenerosAnime {
    Action,
    Adventure,
    Adventures,
    Cars,
    Comedy,
    Dementia,
    Demons,
    Drama,
    Echi,
    Fantasy,
    Game,
    Harem,
    Hentai,
    Historical,
    Horror,
    Kids,
    Magic,
    Martial_Arts,
    Mecha,
    Military,
    Music,
    Mystery,
    Parody,
    Police,
    Psychological,
    Romance,
    Samurai,
    School,
    Sci_Fi,
    Seinen,
    Shoujou,
    Shounen,
    Slice_of_Life,
    Space,
    Sports,
    SuperPower,
    Supernatural,
    Thriller,
    Vampire,
    Yaoi,
}