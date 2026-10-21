package com.example.proyecto_parcial2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceholderDao {
    @Query("SELECT * FROM posts_favoritos ORDER BY agregadoEn DESC")
    fun observarFavoritos(): Flow<List<PlaceholderEntity>>

    @Query("SELECT id FROM posts_favoritos")
    fun observarIdsFavoritos(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(post: PlaceholderEntity)

    @Query("DELETE FROM posts_favoritos WHERE id = :id")
    suspend fun eliminarPorId(id: Int)
}
