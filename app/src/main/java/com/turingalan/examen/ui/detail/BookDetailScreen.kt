package com.turingalan.examen.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.turingalan.examen.data.model.Book

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun BookDetailScreen(
    viewModel: BookDetailViewModel = hiltViewModel(),
    onNavegationBack: () -> Unit,
    bookId:Long
) {
    val book: Book? = viewModel.uiState.collectAsState().value
    Scaffold { paddingValues ->
        if (book != null) {
            Column {
                Card(
                    modifier = Modifier.padding(paddingValues)
                        .padding(start = 8.dp, end = 8.dp).fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = book.title,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = book.author
                        )

                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Card(
                        modifier = Modifier.padding(paddingValues)
                            .padding(start = 8.dp, end = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = book.editorial,
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = book.isbn
                            )

                        }
                    }
                    Card(
                        modifier = Modifier.padding(paddingValues)
                            .padding(start = 8.dp, end = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Publicado en "+book.publicationYear.toString(),
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Editado en "+book.editionYear.toString(),
                            )

                        }
                    }
                }
                Button(onClick = onNavegationBack) {
                    Text(text="Volver a la busqueda")
                }
            }

        }else{

        }

    }


}


/*
*
@Composable
fun PokemonDetailScreen(
    id: Int,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    modifier : Modifier = Modifier
){
    val pokemon: Pokemon? by viewModel.getPokemonByID(id).collectAsState()
    Scaffold { paddingValues ->
        if(pokemon != null) {
            Card(
                modifier = Modifier.padding(paddingValues).padding(top = 60.dp, start = 8.dp, end = 8.dp).fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = pokemon!!.artworkId),
                        contentDescription = pokemon!!.name,
                        modifier = Modifier.size(200.dp).fillMaxWidth(),
                    )
                    Spacer(modifier=Modifier.height(16.dp))
                    Text(
                        text = pokemon!!.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = "Tipo: "+pokemon!!.type,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier=Modifier.height(8.dp))

                }
            }
        }else{
            Text(
                text = "Pokemon no encontrado",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold

            )
        }
    }


}*/