package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.data.remote.CambioResponseDto
import com.example.proyecto_parcial2.models.Cambio

/**
 * Segundo mapeador: se encarga de transformar la respuesta de la API de Frankfurter (Cambios)
 * a objetos de dominio de tipo Cambio.
 */
object MapeadorCambios {

    fun mapCambioResponseDtoToCambioList(dto: CambioResponseDto): List<Cambio> {
        val baseCurrency = dto.base ?: "USD"
        val currentDate = dto.date ?: ""
        val ratesMap = dto.rates ?: emptyMap()

        return ratesMap.map { (quoteCurrency, rateValue) ->
            Cambio(
                base = baseCurrency,
                quote = quoteCurrency,
                rate = rateValue,
                date = currentDate
            )
        }
    }
}
