package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.product_module.InsertProductScaffold
import com.example.movieapp.product_module.InsertProductScreen
import com.example.movieapp.product_module.ProductAPIViewModel
import com.example.movieapp.product_module.ProductEditScreen
import com.example.movieapp.product_module.ProductScaffold


    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                val vm = ProductAPIViewModel()
                val nc = rememberNavController()
                NavHost(navController = nc, startDestination = "home"){
                    composable("home"){
                        ProductScaffold(nc, vm)
                    }
                    composable("insert"){
                        InsertProductScaffold(nc, vm)
                    }
                    composable(
                        "edit/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
                        val viewModel: ProductAPIViewModel = viewModel()
                        ProductEditScreen(
                            viewModel = viewModel,
                            productId = productId,
                            onNavigateBack = { nc.popBackStack() }
                        )
                    }
                }
            }
        }
    }


