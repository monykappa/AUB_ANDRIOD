package com.example.movieapp.state_movie_module.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.state_movie_module.viewmodels.StateMovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun stateFavoriteScreen(
    nc: NavController,
    movieVM: StateMovieViewModel = viewModel(),
) {
    Scaffold(topBar = { movieFavoriteTopBar(nc, movieVM) }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn() {
                items(movieVM.cartmovieList.value.size) { index ->
                    Row(horizontalArrangement = Arrangement.Absolute.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "${movieVM.cartmovieList.value[index].name}",
                            modifier = Modifier.padding(10.dp),
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
                        IconButton(onClick = {
                            movieVM.removeFromCart(movieVM.cartmovieList.value[index])
                        }) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }

                    }
                    AsyncImage(
                        modifier = Modifier
                            .size(width = 500.dp, height = 200.dp)
                            .fillParentMaxWidth()
                            .clickable {
                                movieVM.selectedMovie.value = movieVM.cartmovieList.value[index]
                                nc.navigate("detail")

                            },
                        model = movieVM.cartmovieList.value[index].image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun movieFavoriteTopBar(
    nc: NavController,
    movieVM: StateMovieViewModel = viewModel(),
) {
    TopAppBar(
        title = { Text("Your Favorite movie") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFE91E63),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        actions = {
            IconButton(onClick = {
                nc.navigate("home")
            }) {
                Icon(Icons.Default.Home, contentDescription = null)
            }
            IconButton(onClick = {
                movieVM.sortMovieByTitle()
            }) {
                Icon(Icons.Default.List, contentDescription = null)
            }
        }
    )
}


