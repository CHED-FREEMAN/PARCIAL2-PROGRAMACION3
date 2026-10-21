package com.example.proyecto_parcial2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyecto_parcial2.models.Cambio

@Entity(tableName = "cambios_favoritos")
data class CambioEntity(
    @PrimaryKey val id: Int,
    val base: String,
    val quote: String,
    val rate: Double,
    val date: String,
    val agregadoEn: Long = System.currentTimeMillis()
)

fun CambioEntity.aCambio(): Cambio = Cambio(
    base = base,
    quote = quote,
    rate = rate,
    date = date,
    id = id
)

fun Cambio.aCambioEntity(): CambioEntity = CambioEntity(
    id = id,
    base = base,
    quote = quote,
    rate = rate,
    date = date
)
