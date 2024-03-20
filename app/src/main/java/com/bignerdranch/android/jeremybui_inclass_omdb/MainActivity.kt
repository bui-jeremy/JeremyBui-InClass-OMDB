package com.bignerdranch.android.jeremybui_inclass_omdb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var searchButton: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleInput = findViewById(R.id.titleInput)
        searchButton = findViewById(R.id.searchButton)
        resultText = findViewById(R.id.resultText)

        searchButton.setOnClickListener {
            val title = titleInput.text.toString()
            RetrofitService.omdbApi.getMovieDetails(title, "your_api_key_here").enqueue(object : Callback<MovieDetails> {
                override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                    if (response.isSuccessful) {
                        val movieDetails = response.body()
                        resultText.text = "Title: ${movieDetails?.Title}\nYear: ${movieDetails?.Year}"
                        // Add more details as needed
                    } else {
                        resultText.text = "Error fetching details"
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}
