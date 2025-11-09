package com.turingalan.examen.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import com.turingalan.examen.ui.theme.ExamenTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BookSearchScreen(
    viewModel: BookSearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    error:String?=null
){



    Surface(modifier = modifier) {

        val isScreenIsInError = error!=null

        Card(
            modifier = Modifier
                .padding(all = 20.dp)
                .padding( top = 200.dp, bottom = 200.dp)
                .size(height = 250.dp, width = 500.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
        ){
            Spacer(modifier=Modifier.height(16.dp))

            Text(text="Turing Bokkstore",
                modifier = Modifier.padding(start = 80.dp),
                fontFamily = MaterialTheme.typography.titleLargeEmphasized.fontFamily,
                fontSize = 20.sp
                )

            Spacer(modifier=Modifier.height(16.dp))
            Column(modifier = Modifier.padding(8.dp).fillMaxWidth()) {

                OutlinedTextField(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp).fillMaxWidth(),
                    state = viewModel.busquedaParametros,
                    isError = isScreenIsInError,
                    label = { Text("Las Crónicas de Narnia") }
                )

                Text(text = "Introduce tu busqueda", modifier = Modifier.padding(start = 20.dp),
                    fontSize = 14.sp)
                Text(text = error ?: "")
                Button(
                    modifier = Modifier.fillMaxWidth(),
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

