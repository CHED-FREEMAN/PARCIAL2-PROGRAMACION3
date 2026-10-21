package com.example.proyecto_parcial2.data

import com.example.proyecto_parcial2.models.Cambio
import com.example.proyecto_parcial2.models.Cosas
import com.example.proyecto_parcial2.models.Placeholder

/**
 * Repositorio unificado opcional que delega el trabajo a los tres repositorios independientes:
 * - ProductRepository
 * - PlaceholderRepository
 * - CambiosRepository
 */
class AlgoRepository(
    private val productRepository: ProductRepository = ProductRepository(),
    private val placeholderRepository: PlaceholderRepository = PlaceholderRepository(),
    private val cambiosRepository: CambiosRepository = CambiosRepository()
) {
    suspend fun getCosas(): Result<List<Cosas>> = productRepository.getProducts()

    suspend fun getPlaceholders(): Result<List<Placeholder>> = placeholderRepository.getPosts()

    suspend fun getCambios(fromCurrency: String = "USD"): Result<List<Cambio>> = cambiosRepository.getCambios(fromCurrency)
}
