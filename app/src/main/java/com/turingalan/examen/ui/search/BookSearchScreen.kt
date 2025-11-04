package com.turingalan.examen.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookSearch(
    viewModel: BookSearchViewModel = hiltViewModel(),
    onNavegationList: (busqueda:String) -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when(uiState) {
        is SearchUiState.Error -> {
            val errorMessage = (uiState as SearchUiState.Error).message
            BookSearchScreen(error= errorMessage,)
        }
        is SearchUiState.New ->{
            BookSearchScreen()
        }
        is SearchUiState.searched ->{
            onNavegationList(viewModel.busquedaParametros.text.toString())
        }
    }
}

@Composable
fun BookSearchScreen(
    viewModel: BookSearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    error:String?=null
){



    Surface(modifier = modifier) {

        val isScreenIsInError = error!=null

        Card(){
            Text(text="Turing Bokkstore")
            Spacer(modifier=Modifier.height(16.dp))
            Column(modifier = Modifier.padding(8.dp, top=100.dp)) {

                OutlinedTextField(
                    state = viewModel.busquedaParametros,
                    isError = isScreenIsInError
                )
                Text(text = "Introduce tu busqueda")

                Text(text = error ?: "")



                Button(
                    onClick = {
                        viewModel.search()
                    }
                ){
                    Text("Buscar")
                }

            }
        }

    }
}

/*

@Composable
fun TodoCreateScreen(
    viewModel: TodoCreateViewModel = hiltViewModel(),
    onNavegationBak: () -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when(uiState) {
        CreateUiState.Cancelled -> onNavegationBak()
        is CreateUiState.Error -> {
            val errorMessage = (uiState as CreateUiState.Error).message
            CreateForm(errorMessage= errorMessage,)
        }
        is CreateUiState.New ->{
            CreateForm()
        }
        is CreateUiState.created ->{
            onNavegationBak()
        }
    }
}

@Composable
fun CreateForm(
    errorMessage : String?=null,
    modifier: Modifier = Modifier,
    viewModel: TodoCreateViewModel = hiltViewModel(),

){
    Surface(modifier = modifier){
        val isScreenInError = errorMessage!=null
        Column(modifier = Modifier.padding(top = 100.dp)) {
            OutlinedTextField(
                state = viewModel.nameState,
                isError = isScreenInError
            )
            Text(text = errorMessage ?: "")
            OutlinedTextField(
                state = viewModel.typeState,
                isError = isScreenInError
            )
            Text(text = errorMessage ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.create()
                }
            ){
                Text(text = "Crear")
            }
            Button(
                onClick = {
                    viewModel.cancell()
                }
            ){
                Text(text = "Cancelar")
            }

        }

    }
}
 */