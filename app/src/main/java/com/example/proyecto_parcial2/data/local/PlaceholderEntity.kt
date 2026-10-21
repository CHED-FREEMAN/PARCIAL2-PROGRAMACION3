package com.example.proyecto_parcial2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_parcial2.models.Placeholder

@Entity(tableName = "posts_favoritos")
data class PlaceholderEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
    val agregadoEn: Long = System.currentTimeMillis()
)

fun PlaceholderEntity.aPlaceholder(): Placeholder = Placeholder(
    id = id,
    title = title,
    body = body
)

fun Placeholder.aPlaceholderEntity(): PlaceholderEntity = PlaceholderEntity(
    id = id,
    title = title,
    body = body
)
