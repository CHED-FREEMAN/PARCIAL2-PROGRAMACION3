package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.data.remote.Retrofit
import com.example.proyecto_parcial2.models.Placeholder

class PlaceholderRepository {

    suspend fun getPosts(): Result<List<Placeholder>> {
        return try {
            val response = Retrofit.apiPlaceholder.getPosts()
            if (response.isSuccessful && response.body() != null) {
                val postsDto = response.body() ?: emptyList()
                val listaPlaceholders = MapeadorProductoYPlaceholder.mapPostDtoListToPlaceholderList(postsDto)
                Result.success(listaPlaceholders)
            } else {
                Result.failure(Exception("Error al obtener posts: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
