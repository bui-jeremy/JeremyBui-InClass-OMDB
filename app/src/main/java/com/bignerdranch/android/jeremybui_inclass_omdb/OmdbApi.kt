package com.bignerdranch.android.jeremybui_inclass_omdb

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface OmdbApiService {
    @GET("/")
    fun searchMovieByTitle(
        @Query("t") title: String,
        @Query("apikey") apiKey: String
    ): Call<MovieResponse>
}
