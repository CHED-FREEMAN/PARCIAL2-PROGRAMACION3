package com.example.proyecto_parcial2.ui.screens.placeholders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_parcial2.data.PlaceholderFavoritosRepository
import com.example.proyecto_parcial2.data.PlaceholderRepository
import com.example.proyecto_parcial2.data.local.ProductDatabase
import com.example.proyecto_parcial2.models.Placeholder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PlaceholdersUiState(
    val posts: List<Placeholder> = emptyList(),
    val cargando: Boolean = false,
    val error: String? = null,
    val favoritosIds: Set<Int> = emptySet()
)

class PlaceholdersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PlaceholderRepository()
    private val favoritosRepository = PlaceholderFavoritosRepository(
        ProductDatabase.obtener(application).placeholderDao()
    )

    private val _estadoApi = MutableStateFlow(PlaceholdersUiState(cargando = true))

    val uiState: StateFlow<PlaceholdersUiState> = combine(
        _estadoApi,
        favoritosRepository.observarIdsFavoritos()
    ) { estadoApi, ids ->
        estadoApi.copy(favoritosIds = ids)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PlaceholdersUiState(cargando = true)
    )

    init {
        loadPosts()
    }

    fun loadPosts() {
        _estadoApi.update { it.copy(cargando = true, error = null) }
        viewModelScope.launch {
            repository.getPosts()
                .onSuccess { list ->
                    _estadoApi.update { it.copy(posts = list, cargando = false) }
                }
                .onFailure { error ->
                    _estadoApi.update { it.copy(cargando = false, error = error.localizedMessage ?: "Error desconocido") }
                }
        }
    }

    fun alternarFavorito(post: Placeholder) {
        val esFavorito = post.id in uiState.value.favoritosIds
        viewModelScope.launch {
            if (esFavorito) {
                favoritosRepository.quitar(post.id)
            } else {
                favoritosRepository.agregar(post)
            }
        }
    }
}
