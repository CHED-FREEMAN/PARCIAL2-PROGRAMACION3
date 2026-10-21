package com.example.proyecto_parcial2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_parcial2.models.Cambio
import com.example.proyecto_parcial2.models.Cosas
import com.example.proyecto_parcial2.models.Placeholder

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

fun Placeholder.aEntidad(): ProductEntity = ProductEntity(
    id = id,
    titulo = title,
    descripcion = body,
    categoria = "Post",
    precio = 0.0,
    porcentajeDescuento = 0.0,
    thumbnail = ""
)

fun Cambio.aEntidad(): ProductEntity = ProductEntity(
    id = id,
    titulo = "$base a $quote",
    descripcion = "Tasa: $rate (Fecha: $date)",
    categoria = "Divisa",
    precio = rate,
    porcentajeDescuento = 0.0,
    thumbnail = ""
)
