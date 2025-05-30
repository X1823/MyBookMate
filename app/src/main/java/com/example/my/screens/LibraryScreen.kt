package com.example.my.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Book(val title: String, val progress: Float)

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    val books = listOf(
        Book("Book A", 0.3f),
        Book("Book B", 0.7f),
        Book("Book C", 1.0f)
    )

    Column(modifier = modifier.padding(16.dp)) {
        Text("My Library", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(books) { book ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = book.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(progress = book.progress)
                    }
                }
            }
        }
    }
}
