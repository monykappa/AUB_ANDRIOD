package com.example.movieapp.state_movie_module

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.state_movie_module.screens.stateDetailScreen
import com.example.movieapp.state_movie_module.screens.stateFavoriteScreen
import com.example.movieapp.state_movie_module.screens.stateMovieScreen
import com.example.movieapp.state_movie_module.viewmodels.StateMovieViewModel

@Composable
fun stateMovieApp(){
    val nc: NavHostController = rememberNavController()
    val movieVM :StateMovieViewModel = viewModel()
    NavHost(navController = nc, startDestination = "home" ){
        composable(route="home"){
            stateMovieScreen(nc, movieVM = movieVM)
        }
        composable(route="detail"){
            stateDetailScreen(nc,movieVM = movieVM)
        }
        composable(route="favorite"){
            stateFavoriteScreen(nc,movieVM = movieVM)
        }
    }
}
