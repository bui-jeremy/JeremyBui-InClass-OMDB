package com.bignerdranch.android.jeremybui_inclass_omdb

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call


private const val API_KEY = "3b7f093d"

interface OmdbApi {
    @GET(".")
    fun getMovieDetails(@Query("t") title: String, @Query("apikey") apiKey: String): Call<MovieDetails>
}

