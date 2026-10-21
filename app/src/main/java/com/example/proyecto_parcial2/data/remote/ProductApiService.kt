package com.example.proyecto_parcial2.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Response<ProductResponse>
}
