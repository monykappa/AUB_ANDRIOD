package com.example.movieapp.product_module

import com.google.gson.annotations.SerializedName


data class Product (

    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String,
    @SerializedName("body") var body : String,
    @SerializedName("qty") var qty : Int,
    @SerializedName("price") var price : String,
    @SerializedName("image") var image : String,
    @SerializedName("category") var category : String

)