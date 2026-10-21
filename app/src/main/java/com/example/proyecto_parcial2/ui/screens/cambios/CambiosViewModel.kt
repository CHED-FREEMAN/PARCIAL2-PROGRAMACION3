package com.example.proyecto_parcial2.ui.screens.cambios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_parcial2.data.CambiosRepository
import com.example.proyecto_parcial2.models.Cambio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface CambiosUiState {
    data object Loading : CambiosUiState
    data class Success(val cambios: List<Cambio>, val baseCurrency: String) : CambiosUiState
    data class Error(val message: String) : CambiosUiState
}

class CambiosViewModel(
    private val repository: CambiosRepository = CambiosRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<CambiosUiState>(CambiosUiState.Loading)
    val uiState: StateFlow<CambiosUiState> = _uiState.asStateFlow()

    private val _currentBase = MutableStateFlow("USD")
    val currentBase: StateFlow<String> = _currentBase.asStateFlow()

    init {
        loadCambios("USD")
    }

    fun loadCambios(fromCurrency: String = _currentBase.value) {
        _currentBase.value = fromCurrency
        viewModelScope.launch {
            _uiState.value = CambiosUiState.Loading
            repository.getCambios(fromCurrency)
                .onSuccess { list ->
                    _uiState.value = CambiosUiState.Success(list, fromCurrency)
                }
                .onFailure { error ->
                    _uiState.value = CambiosUiState.Error(error.localizedMessage ?: "Error desconocido")
                }
        }
    }
}
