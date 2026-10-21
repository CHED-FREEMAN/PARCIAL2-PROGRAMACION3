package com.example.proyecto_parcial2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM productos_favoritos ORDER BY agregadoEn DESC")
    fun observarFavoritos(): Flow<List<ProductEntity>>

    @Query("SELECT id FROM productos_favoritos")
    fun observarIdsFavoritos(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(producto: ProductEntity)

    @Query("DELETE FROM productos_favoritos WHERE id = :id")
    suspend fun eliminarPorId(id: Int)
}
