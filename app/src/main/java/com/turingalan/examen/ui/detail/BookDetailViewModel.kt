package com.turingalan.examen.ui.detail

import androidx.activity.result.launch
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel  @Inject constructor(
    private val _repository: BookRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow<Book?>(null)
    var uiState: StateFlow<Book?> = _uiState.asStateFlow()
    private val bookId: Long = savedStateHandle["id"] ?: 0L
    init {
        if (bookId > 0L) {
            loadBook()
        }
    }
    private fun loadBook() {
        viewModelScope.launch {
            _uiState.value = _repository.readOne(bookId)
        }
    }


}
/*
): ViewModel(){
    private val _uiState = MutableStateFlow<List<Book>?>(null)
    val uiState: StateFlow<List<Book>?> = _uiState.asStateFlow()

    init {
        loadBook()
    }

    private fun loadBook() {
        viewModelScope.launch {
            _uiState.value = _repository.readAll()
        }
    }

}
 */

/*    fun getPokemonById(id: Int) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.New
            delay(1000)
            _repository.getPokemonByID(id)
                .catch { exception ->
                    _uiState.value = DetailUiState.Error(exception.message ?: "Error desconocido")
                }
                .collect { pokemon ->
                    if (pokemon != null) {
                        _uiState.value = DetailUiState.Loaded(pokemon)
                    } else {
                        _uiState.value = DetailUiState.Error("Pokémon no encontrado con id: $id")
                    }
                }
        }

    }*/