package com.example.proyecto_parcial2.data.remote

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    val products: List<ProductDto>? = null
)

data class ProductDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("discountPercentage")
    val discountPercentage: Double? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null
)
