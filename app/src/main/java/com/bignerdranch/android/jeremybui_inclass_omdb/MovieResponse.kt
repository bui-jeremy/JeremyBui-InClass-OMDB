package com.bignerdranch.android.jeremybui_inclass_omdb

import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "Title") val title: String,
    @Json(name = "Year") val year: String,
    @Json(name = "Poster") val poster: String
)
