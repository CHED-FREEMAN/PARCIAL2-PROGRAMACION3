package com.example.proyecto_parcial2.ui.screens.placeholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_parcial2.data.PlaceholderRepository
import com.example.proyecto_parcial2.models.Placeholder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PlaceholdersUiState {
    data object Loading : PlaceholdersUiState
    data class Success(val posts: List<Placeholder>) : PlaceholdersUiState
    data class Error(val message: String) : PlaceholdersUiState
}

class PlaceholdersViewModel(
    private val repository: PlaceholderRepository = PlaceholderRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<PlaceholdersUiState>(PlaceholdersUiState.Loading)
    val uiState: StateFlow<PlaceholdersUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PlaceholdersUiState.Loading
            repository.getPosts()
                .onSuccess { list ->
                    _uiState.value = PlaceholdersUiState.Success(list)
                }
                .onFailure { error ->
                    _uiState.value = PlaceholdersUiState.Error(error.localizedMessage ?: "Error desconocido")
                }
        }
    }
}
