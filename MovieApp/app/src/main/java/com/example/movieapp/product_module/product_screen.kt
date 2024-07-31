package com.example.movieapp.product_module


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.movieapp.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductScaffold(vm: ProductViewModel) {
    LaunchedEffect(Unit) {
        vm.getProductList() // Call getProductList() inside a coroutine scope
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Screen") },
                actions = {
                    IconButton(onClick = {
                        // Optionally, you can call getProductList() directly here as well
                        // vm.getProductList()
                    }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ProductBody(vm)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductBody(vm: ProductViewModel) {
    when {
        vm.isLoading -> {
            CircularProgressIndicator()
        }
        vm.errorMessage.isNotEmpty() -> {
            Text(vm.errorMessage)
        }
        else -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(vm.productList) { product ->
                    ProductItem(product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(item: Product) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .border(1.dp, Color.LightGray)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(120.dp)
                .background(Color.White)

        ) {
            val imageUrl = "${item.image}"  // Assuming PRODUCT_BASE_URL is correctly defined
            val painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    crossfade(true)  // Enable crossfade animation
                }
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.title)
        Text(text = "$${item.price}")
        Text(text = item.category)
        Spacer(modifier = Modifier.height(20.dp))
    }
}