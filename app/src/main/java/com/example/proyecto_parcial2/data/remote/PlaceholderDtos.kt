package com.example.proyecto_parcial2.data.remote

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null
)
