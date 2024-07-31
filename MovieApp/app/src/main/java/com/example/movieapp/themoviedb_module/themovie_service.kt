package com.example.movieapp.themoviedb_module

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val originalUrl = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&page=1&api_key=YOUR_KEY"
const val TheMovieDB_BASE_URL = "https://api.themoviedb.org/3/discover/"

interface TheMovieService {
    @GET("movie")
    suspend fun getMovies(
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "bf38baadc96e331a8a1ca2e3e3be78d2",
        ): TheMovie

    companion object {
        private var apiService: TheMovieService? = null
        fun getInstance(): TheMovieService {
            if (apiService == null) {
                val gson = GsonBuilder().setLenient().create()
                apiService = Retrofit.Builder()
                    .baseUrl(TheMovieDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(TheMovieService::class.java)
            }
            return apiService!!
        }
    }
}