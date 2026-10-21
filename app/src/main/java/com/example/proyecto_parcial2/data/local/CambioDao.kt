package com.example.proyecto_parcial2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CambioDao {
    @Query("SELECT * FROM cambios_favoritos ORDER BY agregadoEn DESC")
    fun observarFavoritos(): Flow<List<CambioEntity>>

    @Query("SELECT id FROM cambios_favoritos")
    fun observarIdsFavoritos(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(cambio: CambioEntity)

    @Query("DELETE FROM cambios_favoritos WHERE id = :id")
    suspend fun eliminarPorId(id: Int)
}
