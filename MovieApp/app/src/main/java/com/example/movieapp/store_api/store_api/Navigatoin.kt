package com.example.movieapp.store_api

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComponent(storeViewModel: StoreViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "storeScreen") {
        composable("storeScreen") {
            StoreScreen(storeViewModel, navController)
        }
        composable("detailScreen/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toLongOrNull()
            val product = storeViewModel.productList.find { it.id == productId }
            product?.let { ProductDetailScreen(it, navController) }
        }
    }
}
