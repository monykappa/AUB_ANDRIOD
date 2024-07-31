package com.example.movieapp.store_api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val STORE_BASE_URL = "https://fakestoreapi.com/"

interface StoreAPIService {
    @GET("products")
    suspend fun getProducts(): List<StoreModel>

    companion object {
        private var apiService: StoreAPIService? = null
        fun getInstance(): StoreAPIService {
            if (apiService == null) {
                val gson = GsonBuilder().setLenient().create()
                apiService = Retrofit.Builder()
                    .baseUrl(STORE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(StoreAPIService::class.java)
            }
            return apiService!!
        }
    }
}
