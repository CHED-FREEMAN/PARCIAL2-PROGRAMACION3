package com.example.proyecto_parcial2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_parcial2.models.Cosas

@Entity(tableName = "productos_favoritos")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val titulo: String,
    val descripcion: String,
    val categoria: String,
    val precio: Double,
    val porcentajeDescuento: Double,
    val thumbnail: String,
    val agregadoEn: Long = System.currentTimeMillis()
)

fun ProductEntity.aCosas(): Cosas = Cosas(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    categoria = categoria,
    precio = precio,
    porcentajeDescuento = porcentajeDescuento,
    thumbnail = thumbnail
)

fun Cosas.aEntidad(): ProductEntity = ProductEntity(
    id = id,
    titulo = titulo,
    descripcion = descripcion,
    categoria = categoria,
    precio = precio,
    porcentajeDescuento = porcentajeDescuento,
    thumbnail = thumbnail
)
