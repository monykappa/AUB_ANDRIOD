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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScaffold(navController: NavController, vm: ProductAPIViewModel) {
    LaunchedEffect(Unit) { vm.getProductList() }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Product Screen") },
            actions = {
                IconButton(onClick = {
                    vm.getProductList(results = 5)
                }) {
                    Text("5")
                }
                IconButton(onClick = {
                    vm.getProductList(results = 25)
                }) {
                    Text("25")
                }
                IconButton(onClick = {
                    navController.navigate("insert")
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        )
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()

            ,
            contentAlignment = Alignment.Center
        ) {
            ProductBody(vm, navController)
        }
    }
}

@Composable
fun ProductBody(vm: ProductAPIViewModel, navController: NavController) {
    if (vm.isLoading) {
        CircularProgressIndicator()
    } else if (vm.errorMessage.isNotEmpty()) {
        Text(vm.errorMessage)
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(vm.productList.size) {
                ProductItem(vm.productList[it], vm, navController)
            }
        }
    }
}
@Composable
fun ProductItem(item: Product, vm: ProductAPIViewModel, navController: NavController) {
    Card(
        modifier = Modifier.padding(5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Start, // Align items to start
            verticalAlignment = Alignment.CenterVertically // Center vertically
        ) {
            // Load and display the product image on the left
            item.image?.let {
                val painter = rememberImagePainter(it)
                Image(
                    painter = painter,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(100.dp) // Adjust the size as needed
                        .padding(end = 10.dp) // Space between image and text
                )
            }

            // Text content on the right
            Column(
                modifier = Modifier
                    .weight(1f) // Take up the remaining space
                    .padding(10.dp)
            ) {
                Text("${item.title}", modifier = Modifier.padding(bottom = 4.dp))
                Text("${item.price}", modifier = Modifier.padding(bottom = 4.dp))
                Text("${item.category}", modifier = Modifier.padding(bottom = 4.dp))

                if (vm.isLoading) {
                    CircularProgressIndicator()
                }

                if (vm.errorMessage.isNotEmpty()) {
                    Text(vm.errorMessage, color = Color.Red)
                }
            }

            // Action buttons (edit and delete) on the right side
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("edit/${item.id}")
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = {
                    vm.deleteProduct(item.id)
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}