package com.example.movieapp.product_module

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Operation
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ProductAPIViewModel : ViewModel() {
    private val _productList = mutableStateListOf<Product>()
    var errorMessage: String by mutableStateOf("")
    var successMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val productList: List<Product>
        get() = _productList

    private val _currentProduct = MutableStateFlow<Product?>(null)
    val currentProduct: StateFlow<Product?>
        get() = _currentProduct

    fun getProductList(results: Int = 500) {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductAPIService.getInstance()
            try {
                _productList.clear()
                _productList.addAll(apiService.getProductList(results = results))
                successMessage = "Product list fetched successfully"
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductAPIService.getInstance()
            try {
                val response: String = apiService.insertProduct(
                    title = product.title,
                    body = product.body,
                    price = product.price,
                    qty = product.qty,
                    category = product.category,
                    image = product.image,
                )
                if (response == "success") {
                    successMessage = "Product inserted successfully"
                    getProductList() // Refresh the product list after insertion
                } else {
                    errorMessage = "Error: $response"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message.toString()}"
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductAPIService.getInstance()
            try {
                val response: String = apiService.deleteProduct(id = id)
                if (response == "success") {
                    successMessage = "Product deleted successfully"
                    getProductList()
                } else {
                    errorMessage = "Error: $response"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message.toString()}"
            } finally {
                isLoading = false
            }
        }
    }

    fun getProduct(id: String) {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductAPIService.getInstance()
            try {
                _currentProduct.value = apiService.getProduct(id = id)
                successMessage = "Product fetched successfully"
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductAPIService.getInstance()
            try {
                val response: String = apiService.updateProduct(
                    id = product.id,
                    title = product.title,
                    body = product.body,
                    price = product.price,
                    qty = product.qty,
                    category = product.category,
                    image = product.image
                )
                if (response == "success") {
                    successMessage = "Product updated successfully"
                    getProductList() // Refresh the product list after update
                } else {
                    errorMessage = "Error: $response"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message.toString()}"
            } finally {
                isLoading = false
            }
        }
    }
}
