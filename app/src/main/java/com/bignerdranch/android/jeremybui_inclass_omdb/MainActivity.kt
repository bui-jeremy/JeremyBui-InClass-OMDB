package com.bignerdranch.android.jeremybui_inclass_omdb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var searchButton: Button
    private lateinit var movieTitle: TextView
    private lateinit var movieYear: TextView
    private lateinit var moviePoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleInput = findViewById(R.id.titleInput)
        searchButton = findViewById(R.id.searchButton)
        movieTitle = findViewById(R.id.movieTitleTextView)
        movieYear = findViewById(R.id.movieYearTextView)
        moviePoster = findViewById(R.id.moviePosterImageView)

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val omdbApi = retrofit.create(OmdbApiService::class.java)

        searchButton.setOnClickListener {
            val title = titleInput.text.toString()
            omdbApi.searchMovieByTitle(title, "3b7f093d").enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    val movieResponse = response.body()

                    if (movieResponse != null) {
                        if (movieResponse.title != null) {
                            movieTitle.text = movieResponse.title
                        } else {
                            movieTitle.text = getString(R.string.title_not_found)
                        }
                        if (movieResponse.year != null) {
                            movieYear.text = movieResponse.year
                        } else {
                            movieYear.text = getString(R.string.year_not_found)
                        }
                        if (movieResponse.poster != null && movieResponse.poster.isNotEmpty()) {
                            Picasso.get().load(movieResponse.poster).into(moviePoster)
                        }
                    } else {
                        movieTitle.text = getString(R.string.title_not_found)
                        movieYear.text = getString(R.string.year_not_found)
                    }
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    movieTitle.text = "Failure: " + t.message
                }
            })
        }
    }
}
