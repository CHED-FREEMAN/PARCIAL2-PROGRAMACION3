package com.example.proyecto_parcial2.data.remote

import com.google.gson.annotations.SerializedName

data class CambioResponseDto(
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("rates")
    val rates: Map<String, Double>? = null
)
