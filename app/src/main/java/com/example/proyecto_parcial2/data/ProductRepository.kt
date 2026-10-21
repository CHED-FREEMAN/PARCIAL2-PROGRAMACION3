package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.data.remote.Retrofit
import com.example.proyecto_parcial2.models.Cosas

class ProductRepository {

    suspend fun getProducts(): Result<List<Cosas>> {
        return try {
            val response = Retrofit.apiProduct.getProducts()
            if (response.isSuccessful && response.body() != null) {
                val productsDto = response.body()?.products ?: emptyList()
                val listaCosas = MapeadorProductoYPlaceholder.mapProductDtoListToCosasList(productsDto)
                Result.success(listaCosas)
            } else {
                Result.failure(Exception("Error al obtener productos: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
