package com.example.appscriptapi.model

data class AnimeResponse(
    val status: String,
    val type: String,
    val data: List<Anime>
)