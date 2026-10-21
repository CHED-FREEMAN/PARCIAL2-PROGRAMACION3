package com.example.proyecto_parcial2.models

class Cosas(
    val id: Int = 0,
    val titulo: String,
    var descripcion: String,
    val categoria: String,
    var precio: Double,
    var porcentajeDescuento: Double,
    val thumbnail: String,
)
