package com.example.movieapp.product_module

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val PRODUCT_BASE_URL = "http://192.168.93.238:3000/"





interface ProductService {
    @GET("read.php")
    suspend fun getProductList(
        @Query("key") key: String = "d033e22ae348aeb5660fc2140aec35850c4da997"
    ): List<Product>

    companion object {
        private var service: ProductService? = null
        fun getInstance(): ProductService {
            if (service == null) {
                val gson = GsonBuilder().setLenient().create()
                service = Retrofit.Builder()
                    .baseUrl(PRODUCT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ProductService::class.java)
            }
            return service!!
        }
    }
}
