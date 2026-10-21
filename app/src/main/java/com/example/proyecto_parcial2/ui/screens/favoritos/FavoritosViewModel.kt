package com.example.proyecto_parcial2.ui.screens.favoritos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_parcial2.data.FavoritosRepository
import com.example.proyecto_parcial2.data.local.ProductDatabase
import com.example.proyecto_parcial2.models.Cosas
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class FavoritosUiState(
    val productosFavoritos: List<Cosas> = emptyList(),
    val cargando: Boolean = true
)

class FavoritosViewModel(application: Application) : AndroidViewModel(application) {

    private val favoritosRepository = FavoritosRepository(
        ProductDatabase.obtener(application).productDao()
    )

    val uiState: StateFlow<FavoritosUiState> = favoritosRepository.observarProductosFavoritos()
        .map { lista -> FavoritosUiState(productosFavoritos = lista, cargando = false) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavoritosUiState()
        )

    fun quitarProducto(producto: Cosas) {
        viewModelScope.launch {
            favoritosRepository.quitarProducto(producto.id)
        }
    }
}
