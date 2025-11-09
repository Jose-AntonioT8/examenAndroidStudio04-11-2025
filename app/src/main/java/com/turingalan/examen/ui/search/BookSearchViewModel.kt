package com.turingalan.examen.ui.search

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class BookSearchViewModel @Inject constructor(): ViewModel()
{
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.New)
    val uiState : StateFlow<SearchUiState>
        get () = _uiState.asStateFlow()
    val busquedaParametros = TextFieldState()
    private fun acceptSearch():Boolean = busquedaParametros.text.isNotBlank()
    fun search(){
        if(acceptSearch()){
            _uiState.value = SearchUiState.searched
        }else{
            _uiState.value = SearchUiState.Error("Debes completa el campo")
        }
    }
}
sealed class SearchUiState(){
    object New : SearchUiState()
    data class Error(val message :String): SearchUiState()
    object searched : SearchUiState()

}