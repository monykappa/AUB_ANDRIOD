package com.example.movieapp.state_movie_module.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.aibfriday.state_movie_module.models.StateMovieModel
import com.example.aibfriday.state_movie_module.models.stateMovieList1
import movieList1

class StateMovieViewModel: ViewModel() {
    val movieList : MutableState<List<StateMovieModel>> = mutableStateOf(stateMovieList1)
    var selectedMovie: MutableState<StateMovieModel> = mutableStateOf(StateMovieModel())
    val cartmovieList : MutableState<MutableList<StateMovieModel>> = mutableStateOf(
        mutableListOf()
    )

    private var sortedAZ: MutableState<Boolean> = mutableStateOf(false)


    fun sortMovieByTitle(){
        sortedAZ.value = !sortedAZ.value
        if(sortedAZ.value){
            movieList.value = movieList.value.sortedBy { it.name }
        }else{
            movieList.value = movieList.value.sortedByDescending { it.name }
        }
    }
    fun removeMovie(index: Int) {
        if (index >= 0 && index < movieList.value.size) {
            val newList = movieList.value.toMutableList()
            newList.removeAt(index)
            movieList.value = newList
        }
    }
    fun addToCart(movie: StateMovieModel){
        cartmovieList.value.add(movie)
    }
    fun removeFromCart(movie: StateMovieModel){
        val newList = cartmovieList.value.toMutableList()
        newList.remove(movie)
        cartmovieList.value = newList

    }
    fun cartSize(): Int {
        var size = cartmovieList.value.size
        return size
    }
}



