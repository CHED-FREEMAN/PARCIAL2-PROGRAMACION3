package com.example.proyecto_parcial2.models

class Cambio(
    val base: String,
    val quote: String,
    var rate: Double,
    var date: String,
    val id: Int = (base + quote).hashCode()
)
