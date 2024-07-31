package com.example.movieapp.store_api

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class StoreViewModel : ViewModel() {
    private val _productList = mutableStateListOf<StoreModel>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val productList: List<StoreModel>
        get() = _productList

    var isSortAscending: Boolean by mutableStateOf(true)

    fun getProducts() {
        viewModelScope.launch {
            isLoading = true
            val apiService = StoreAPIService.getInstance()
            try {
                _productList.clear()
                _productList.addAll(apiService.getProducts())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }
    fun toggleSortOrder() {
        isSortAscending = !isSortAscending
        sortProducts()
    }

    private fun sortProducts() {
        if (isSortAscending) {
            _productList.sortBy { it.price }
        } else {
            _productList.sortByDescending { it.price }
        }
    }

}

