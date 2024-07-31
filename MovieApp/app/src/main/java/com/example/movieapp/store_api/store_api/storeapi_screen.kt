package com.example.movieapp.store_api

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(storeViewModel: StoreViewModel, navController: NavHostController) {
    LaunchedEffect(Unit) { storeViewModel.getProducts() }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Product List") },
            actions = {
                IconButton(onClick = { storeViewModel.getProducts() }) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
                IconButton(
                    modifier = Modifier
                        .width(80.dp)
                        .border(1.dp , Color.Black)
                    ,
                    onClick = { storeViewModel.toggleSortOrder() }) {
                    Text(
                        text = if (storeViewModel.isSortAscending) "Low to High" else "High to Low",
                        color = Color.Black,
                    )
                }
            }
        )
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { StoreBody(storeViewModel, navController) }
    }
}


@Composable
fun StoreBody(storeViewModel: StoreViewModel, navController: NavHostController) {
    if (storeViewModel.isLoading) {
        CircularProgressIndicator()
    } else if (storeViewModel.errorMessage.isNotEmpty()) {
        Text(storeViewModel.errorMessage)
    } else {
        val productList = storeViewModel.productList
        val chunkedProducts = productList.chunked(2)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(chunkedProducts.size) { rowIndex ->
                val rowProducts = chunkedProducts[rowIndex]
                StoreRow(rowProducts, navController)
            }
        }
    }
}



@Composable
fun StoreRow(products: List<StoreModel>, navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        products.forEach { product ->
            StoreItem(product, navController, Modifier.weight(1f))
        }
    }
}




@Composable
fun StoreItem(product: StoreModel, navController: NavHostController, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(250.dp)
            .clickable {
                navController.navigate("detailScreen/${product.id}")
            }
            .then(modifier),
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Gray)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(text = product.title)
            Text(text = "Price: $${product.price}" , color = Color.Red)
        }
    }
}
