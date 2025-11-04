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

/*
*
    fun onCreate() = nameState.text.isNotEmpty() && typeState.text.isNotEmpty()
    fun create(){
        if(onCreate()){
            _repository.addPokemon(
                Pokemon(
                    id = generateID(),
                    name = nameState.text.toString(),
                    type = typeState.text.toString(),
                    spriteId = R.drawable.sprite_1,
                    artworkId = R.drawable.artwork_1
                )
            )
            _uiState.value = CreateUiState.created
        }else{
            _uiState.value = CreateUiState.Error("Debes completar todos los campos")
        }
    }
    fun cancell(){
        _uiState.value = CreateUiState.Cancelled
    }


}

sealed class CreateUiState{
    object New : CreateUiState()
    data class Error(val message :String): CreateUiState()
    object created : CreateUiState()
    object Cancelled : CreateUiState()

}*/