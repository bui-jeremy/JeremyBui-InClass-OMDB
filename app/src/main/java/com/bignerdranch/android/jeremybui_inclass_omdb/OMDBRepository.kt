package com.bignerdranch.android.jeremybui_inclass_omdb


import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val omdbApi: OmdbApi = retrofit.create(OmdbApi::class.java)
}
