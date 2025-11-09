package com.turingalan.examen.ui.results

import android.graphics.Color.green
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turingalan.examen.data.model.Book


@Composable
fun BookResult(
    viewModel: BookResultViewModel = hiltViewModel(),
    onShowDetail: (Long) -> Unit,
    onNavigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is BookUiState.cargando -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is BookUiState.exito -> {
            BookResultScreen(
                books = state.books,
                onShowDetail = onShowDetail,
                onNavigateBack = onNavigateBack
            )
        }
        is BookUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onNavigateBack = onNavigateBack
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BookResultScreen(
    books: List<Book>,
    onShowDetail: (Long) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold { paddingValues ->


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(top=28.dp, start=16.dp, end = 16.dp)
                    .height(40.dp),
                colors =  CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ){
                Text(modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    text = "Se han encontrado "+books.size+" libros")
            }
            LazyColumn(
                contentPadding = PaddingValues(8.dp)
            ) {

                items(
                    items = books,
                    key = { it.id }
                ) { book ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { onShowDetail(book.id) },
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = book.title)
                            Text(text = book.author)
                            Text(text = book.editorial)
                        }
                    }
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = onNavigateBack
            ) {
                Text("Volver a la busqueda")
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String, onNavigateBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Error: $message")
            Button(onClick = onNavigateBack, modifier = Modifier.padding(top = 16.dp)) {
                Text("Volver a la busqueda")
            }
        }
    }
}
