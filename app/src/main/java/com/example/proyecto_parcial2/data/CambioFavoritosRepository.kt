package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.data.local.CambioDao
import com.example.proyecto_parcial2.data.local.aCambio
import com.example.proyecto_parcial2.data.local.aCambioEntity
import com.example.proyecto_parcial2.models.Cambio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CambioFavoritosRepository(private val cambioDao: CambioDao) {

    fun observarFavoritos(): Flow<List<Cambio>> =
        cambioDao.observarFavoritos().map { entidades ->
            entidades.map { it.aCambio() }
        }

    fun observarIdsFavoritos(): Flow<Set<Int>> =
        cambioDao.observarIdsFavoritos().map { ids -> ids.toSet() }

    suspend fun agregar(cambio: Cambio) = cambioDao.insertar(cambio.aCambioEntity())

    suspend fun quitar(id: Int) = cambioDao.eliminarPorId(id)
}
