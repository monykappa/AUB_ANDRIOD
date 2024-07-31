// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.movieapp.store_api

data class StoreModel (
    val image: String,
    val price: Double,
    val rating: Rating,
    val description: String,
    val id: Long,
    val title: String,
    val category: String
)

data class Rating (
    val rate: Double,
    val count: Long
)
