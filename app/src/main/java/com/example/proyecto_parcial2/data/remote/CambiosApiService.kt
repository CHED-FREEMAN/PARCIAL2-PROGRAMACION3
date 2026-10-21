package com.example.proyecto_parcial2.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CambiosApiService {
    @GET("v1/latest")
    suspend fun getLatestRates(
        @Query("from") from: String? = "USD"
    ): Response<CambioResponseDto>
}
