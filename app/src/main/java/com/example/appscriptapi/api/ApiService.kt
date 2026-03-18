package com.example.appscriptapi.api

import com.example.appscriptapi.model.Anime
import com.example.appscriptapi.model.AnimeResponse
import retrofit2.Response
import retrofit2.http.*

interface AnimeApiService {
    @GET("exec")
    suspend fun getAnimes(
        @Query("apiKey") apiKey: String = "t9F5xDr7t134Ff"
    ): Response<AnimeResponse>

    @POST("exec")
    suspend fun postAnime(
        @Query("apiKey") apiKey: String = "t9F5xDr7t134Ff",
        @Body anime: Anime
    ): Response<Unit>
}