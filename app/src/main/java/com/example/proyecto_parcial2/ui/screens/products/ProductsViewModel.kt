package com.example.proyecto_parcial2.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_parcial2.data.ProductRepository
import com.example.proyecto_parcial2.models.Cosas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProductsUiState {
    data object Loading : ProductsUiState
    data class Success(val products: List<Cosas>) : ProductsUiState
    data class Error(val message: String) : ProductsUiState
}

class ProductsViewModel(
    private val repository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading)
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = ProductsUiState.Loading
            repository.getProducts()
                .onSuccess { list ->
                    _uiState.value = ProductsUiState.Success(list)
                }
                .onFailure { error ->
                    _uiState.value = ProductsUiState.Error(error.localizedMessage ?: "Error desconocido")
                }
        }
    }
}
