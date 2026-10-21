package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.data.remote.Retrofit
import com.example.proyecto_parcial2.models.Cambio

class CambiosRepository {

    suspend fun getCambios(fromCurrency: String = "USD"): Result<List<Cambio>> {
        return try {
            val response = Retrofit.apiCambios.getLatestRates(fromCurrency)
            if (response.isSuccessful && response.body() != null) {
                val cambioDto = response.body()!!
                val listaCambios = MapeadorCambios.mapCambioResponseDtoToCambioList(cambioDto)
                Result.success(listaCambios)
            } else {
                Result.failure(Exception("Error al obtener tasas de cambio: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
