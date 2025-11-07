package com.turingalan.examen.ui.results

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookResultViewModel @Inject constructor(
    private val _repository: BookRepository,
    savedStateHandle: SavedStateHandle

): ViewModel(){

    private val busqueda: String = savedStateHandle["result"] ?: ""
    init {
        loadBook()
    }
    private val _uiState = MutableStateFlow<BookUiState>(BookUiState.cargando)
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    private fun loadBook() {
        viewModelScope.launch {
           val lista : List<Book> = _repository.searchBooks(busqueda)
            if (lista.isNotEmpty()){
                _uiState.value = BookUiState.exito(lista)

            }else{
                _uiState.value = BookUiState.Error("No se pudieron cargar los libros.")

            }


        }
    }

}
sealed interface BookUiState {
    object cargando : BookUiState
    data class exito(val books: List<Book>) : BookUiState
    data class Error(val message: String) : BookUiState
}
/*
@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val _repository: PokemonRepository
) : ViewModel() {
    val pokemons: StateFlow<List<Pokemon>> =
        _repository.getAllPokemons()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}


 */