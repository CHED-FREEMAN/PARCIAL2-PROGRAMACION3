package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.data.local.ProductDao
import com.example.proyecto_parcial2.data.local.aCosas
import com.example.proyecto_parcial2.data.local.aEntidad
import com.example.proyecto_parcial2.models.Cambio
import com.example.proyecto_parcial2.models.Cosas
import com.example.proyecto_parcial2.models.Placeholder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritosRepository(private val productDao: ProductDao) {

    fun observarProductosFavoritos(): Flow<List<Cosas>> =
        productDao.observarFavoritos().map { entidades ->
            entidades.map { it.aCosas() }
        }

    fun observarIdsProductosFavoritos(): Flow<Set<Int>> =
        productDao.observarIdsFavoritos().map { ids -> ids.toSet() }

    suspend fun agregarProducto(producto: Cosas) = productDao.insertar(producto.aEntidad())

    suspend fun agregarPlaceholder(placeholder: Placeholder) = productDao.insertar(placeholder.aEntidad())

    suspend fun agregarCambio(cambio: Cambio) = productDao.insertar(cambio.aEntidad())

    suspend fun quitarProducto(id: Int) = productDao.eliminarPorId(id)
}
