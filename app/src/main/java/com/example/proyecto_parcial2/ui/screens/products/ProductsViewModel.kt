package com.example.proyecto_parcial2.ui.screens.products

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_parcial2.data.FavoritosRepository
import com.example.proyecto_parcial2.data.ProductRepository
import com.example.proyecto_parcial2.data.local.ProductDatabase
import com.example.proyecto_parcial2.models.Cosas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductsUiState(
    val products: List<Cosas> = emptyList(),
    val cargando: Boolean = false,
    val error: String? = null,
    val favoritosIds: Set<Int> = emptySet()
)

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProductRepository()
    private val favoritosRepository = FavoritosRepository(
        ProductDatabase.obtener(application).productDao()
    )

    private val _estadoApi = MutableStateFlow(ProductsUiState(cargando = true))

    val uiState: StateFlow<ProductsUiState> = combine(
        _estadoApi,
        favoritosRepository.observarIdsProductosFavoritos()
    ) { estadoApi, ids ->
        estadoApi.copy(favoritosIds = ids)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProductsUiState(cargando = true)
    )

    init {
        loadProducts()
    }

    fun loadProducts() {
        _estadoApi.update { it.copy(cargando = true, error = null) }
        viewModelScope.launch {
            repository.getProducts()
                .onSuccess { list ->
                    _estadoApi.update { it.copy(products = list, cargando = false) }
                }
                .onFailure { error ->
                    _estadoApi.update { it.copy(cargando = false, error = error.localizedMessage ?: "Error desconocido") }
                }
        }
    }

    fun alternarFavorito(product: Cosas) {
        val esFavorito = product.id in uiState.value.favoritosIds
        viewModelScope.launch {
            if (esFavorito) {
                favoritosRepository.quitarProducto(product.id)
            } else {
                favoritosRepository.agregarProducto(product)
            }
        }
    }
}
