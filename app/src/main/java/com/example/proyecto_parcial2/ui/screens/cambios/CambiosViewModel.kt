package com.example.proyecto_parcial2.ui.screens.cambios

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_parcial2.data.CambioFavoritosRepository
import com.example.proyecto_parcial2.data.CambiosRepository
import com.example.proyecto_parcial2.data.local.ProductDatabase
import com.example.proyecto_parcial2.models.Cambio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CambiosUiState(
    val cambios: List<Cambio> = emptyList(),
    val baseCurrency: String = "USD",
    val cargando: Boolean = false,
    val error: String? = null,
    val favoritosIds: Set<Int> = emptySet()
)

class CambiosViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CambiosRepository()
    private val favoritosRepository = CambioFavoritosRepository(
        ProductDatabase.obtener(application).cambioDao()
    )

    private val _estadoApi = MutableStateFlow(CambiosUiState(cargando = true))

    private val _currentBase = MutableStateFlow("USD")
    val currentBase: StateFlow<String> = _currentBase.asStateFlow()

    val uiState: StateFlow<CambiosUiState> = combine(
        _estadoApi,
        favoritosRepository.observarIdsFavoritos()
    ) { estadoApi, ids ->
        estadoApi.copy(favoritosIds = ids)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = CambiosUiState(cargando = true)
    )

    init {
        loadCambios("USD")
    }

    fun loadCambios(fromCurrency: String = _currentBase.value) {
        _currentBase.value = fromCurrency
        _estadoApi.update { it.copy(cargando = true, error = null, baseCurrency = fromCurrency) }
        viewModelScope.launch {
            repository.getCambios(fromCurrency)
                .onSuccess { list ->
                    _estadoApi.update { it.copy(cambios = list, cargando = false) }
                }
                .onFailure { error ->
                    _estadoApi.update { it.copy(cargando = false, error = error.localizedMessage ?: "Error desconocido") }
                }
        }
    }

    fun alternarFavorito(cambio: Cambio) {
        val esFavorito = cambio.id in uiState.value.favoritosIds
        viewModelScope.launch {
            if (esFavorito) {
                favoritosRepository.quitar(cambio.id)
            } else {
                favoritosRepository.agregar(cambio)
            }
        }
    }
}
