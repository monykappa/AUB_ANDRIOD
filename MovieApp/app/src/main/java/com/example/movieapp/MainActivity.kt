package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieapp.product_module.ProductScaffold
import com.example.movieapp.product_module.ProductViewModel
import com.example.movieapp.themoviedb_module.MovieApp
import com.example.movieapp.themoviedb_module.TheMovieViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = ProductViewModel()
            ProductScaffold(vm)
    }
}
    }

