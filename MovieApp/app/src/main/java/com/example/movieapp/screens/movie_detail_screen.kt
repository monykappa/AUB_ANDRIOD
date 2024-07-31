package com.example.movieapp.screens

import MovieModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.R


//@Preview(showSystemUi = true)
//@Composable
//fun Preview(){
//    val nc = rememberNavController()
//    val selectedMovie = MovieModel(/* provide movie details here */)
//    movieDetailScreen(nc = nc, selectedMovie = selectedMovie)
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun movieDetailScreen(navController: NavController, selectedMovie: MovieModel?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail Page") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE91E63),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            selectedMovie?.let { movie ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        model = movie.image,
                        contentDescription = movie.name,
                        modifier = Modifier.fillMaxSize(),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = movie.name,

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.des,
                    )
                }
            }
        }
    }
}