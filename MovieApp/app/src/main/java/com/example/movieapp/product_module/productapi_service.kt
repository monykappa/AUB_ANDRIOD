package com.example.movieapp.product_module

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

//const val originalUrl = "http://10.0.2.2:8080/aubwed/?key=d033e22ae348aeb5660fc2140aec35850c4da997"
private const val PRODUCT_API_BASE_URL = "http://192.168.91.182:3000"

interface ProductAPIService {
    @GET("read.php")
    suspend fun getProductList(
        @Query("key") key: String = "d033e22ae348aeb5660fc2140aec35850c4da997",
        @Query("results") results: Int = 25,
    ): List<Product>

    @FormUrlEncoded
    @POST("add.php")
    suspend fun insertProduct(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("qty") qty: String,
        @Field("price") price: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Query("key") key: String = "d033e22ae348aeb5660fc2140aec35850c4da997"
    ): String

    @FormUrlEncoded
    @POST("delete.php")
    suspend fun deleteProduct(
        @Field("id") id: String,
        @Query("key") key: String = "d033e22ae348aeb5660fc2140aec35850c4da997"
    ): String

    @GET("edit.php")
    suspend fun getProduct(
        @Query("key") key: String = "d033e22ae348aeb5660fc2140aec35850c4da997",
        @Query("id") id: String
    ): Product

    @FormUrlEncoded
    @POST("edit.php")
    suspend fun updateProduct(
        @Field("id") id: String,
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("qty") qty: String,
        @Field("price") price: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Query("key") key: String = "d033e22ae348aeb5660fc2140aec35850c4da997"
    ): String

    companion object {
        private var apiService: ProductAPIService? = null
        fun getInstance(): ProductAPIService {
            if (apiService == null) {
                val gson = GsonBuilder().setLenient().create()
                apiService = Retrofit.Builder()
                    .baseUrl(PRODUCT_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build().create(ProductAPIService::class.java)
            }
            return apiService!!
        }
    }
}