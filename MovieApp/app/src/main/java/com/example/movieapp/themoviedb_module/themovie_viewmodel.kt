package com.example.movieapp.themoviedb_module

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class TheMovieViewModel : ViewModel() {
    private val _theMovieList = mutableStateListOf<Result>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val theMovieList: List<Result>
        get() = _theMovieList

    fun getMovies() {
        viewModelScope.launch {
            isLoading = true
            val apiService = TheMovieService.getInstance()
            try {
                _theMovieList.clear()
                val movies = apiService.getMovies().results
                _theMovieList.addAll(movies)
                Log.d("TheMovieViewModel", "Movies loaded: ${movies.map { it.id }}")
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("TheMovieViewModel", "Error loading movies: $errorMessage")
            } finally {
                isLoading = false
            }
        }
    }

    fun getMovieById(movieId: Long): Result? {
        val movie = _theMovieList.find { it.id == movieId }
        Log.d("TheMovieViewModel", "getMovieById: movieId=$movieId, movie=$movie")
        return movie
    }

    fun sortMovies(sortBy: String, ascending: Boolean) {
        val sortedList = when (sortBy) {
            "popularity" -> _theMovieList.sortedBy { it.popularity }
            "release date" -> _theMovieList.sortedBy { it.releaseDate }
            "vote average" -> _theMovieList.sortedBy { it.voteAverage }
            else -> _theMovieList.toList()
        }
        if (!ascending) {
            _theMovieList.clear()
            _theMovieList.addAll(sortedList.reversed())
        } else {
            _theMovieList.clear()
            _theMovieList.addAll(sortedList)
        }
    }
}
