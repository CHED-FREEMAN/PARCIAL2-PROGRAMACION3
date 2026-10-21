package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.data.local.PlaceholderDao
import com.example.proyecto_parcial2.data.local.aPlaceholder
import com.example.proyecto_parcial2.data.local.aPlaceholderEntity
import com.example.proyecto_parcial2.models.Placeholder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaceholderFavoritosRepository(private val placeholderDao: PlaceholderDao) {

    fun observarFavoritos(): Flow<List<Placeholder>> =
        placeholderDao.observarFavoritos().map { entidades ->
            entidades.map { it.aPlaceholder() }
        }

    fun observarIdsFavoritos(): Flow<Set<Int>> =
        placeholderDao.observarIdsFavoritos().map { ids -> ids.toSet() }

    suspend fun agregar(post: Placeholder) = placeholderDao.insertar(post.aPlaceholderEntity())

    suspend fun quitar(id: Int) = placeholderDao.eliminarPorId(id)
}
