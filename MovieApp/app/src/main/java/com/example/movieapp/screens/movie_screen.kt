package com.example.movieapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import movieList1



@Composable
fun movieScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
            LazyColumn() {

                items(movieList1.size) {
                    AsyncImage(
                        modifier = Modifier
                            .size(width = 500.dp, height = 300.dp)
                            .fillParentMaxWidth()
                            .clickable {
                                       navController.navigate("detail/${movieList1[it].name}")
                            },
                        model = movieList1[it].image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }

