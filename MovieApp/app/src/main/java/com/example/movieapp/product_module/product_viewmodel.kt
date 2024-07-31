package com.example.movieapp.product_module

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ProductViewModel : ViewModel() {
    private val _productList = mutableStateListOf<Product>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val productList: List<Product>
        get() = _productList

    suspend fun getProductList() {
        isLoading = true
        val apiService = ProductService.getInstance()
        try {
            _productList.clear()
            val newProducts = apiService.getProductList()
            _productList.addAll(newProducts)
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        } finally {
            isLoading = false
        }
    }
}