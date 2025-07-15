package com.example.my.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.my.Book
import com.example.my.BookViewModel

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    bookViewModel: BookViewModel = viewModel()
) {
    val bookList by bookViewModel.allBooks.observeAsState(initial = emptyList())

    var newTitle by remember { mutableStateOf(TextFieldValue("")) }
    var newAuthor by remember { mutableStateOf(TextFieldValue("")) }
    var newProgress by remember { mutableStateOf(TextFieldValue("")) }
    var newCopy by remember { mutableStateOf(TextFieldValue("")) }
    var newNote by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Library", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

        OutlinedTextField(
            value = newTitle,
            onValueChange = { newTitle = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = newAuthor,
            onValueChange = { newAuthor = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = newProgress,
            onValueChange = { newProgress = it },
            label = { Text("Progress") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = newCopy,
            onValueChange = { newCopy = it },
            label = { Text("Copy Info") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = newNote,
            onValueChange = { newNote = it },
            label = { Text("Note") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val progressInt = newProgress.text.toIntOrNull() ?: 0
                bookViewModel.insertBook(
                    title = newTitle.text,
                    author = newAuthor.text,
                    progress = progressInt,
                    copy = newCopy.text,
                    note = newNote.text
                )
                newTitle = TextFieldValue("")
                newAuthor = TextFieldValue("")
                newProgress = TextFieldValue("")
                newCopy = TextFieldValue("")
                newNote = TextFieldValue("")
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Add Book")
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        bookList.forEach { book ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text("${book.title} by ${book.author} (${book.progress}%)\nCopy: ${book.copy}\nNote: ${book.note}")

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {
                        bookViewModel.deleteBook(book)
                    }) {
                        Text("Delete")
                    }

                    Button(onClick = {
                        val updatedProgress = newProgress.text.toIntOrNull() ?: book.progress
                        val updatedBook = book.copy(
                            title = newTitle.text,
                            author = newAuthor.text,
                            progress = updatedProgress,
                            copy = newCopy.text,
                            note = newNote.text
                        )
                        bookViewModel.updateBook(updatedBook)
                    }) {
                        Text("Update")
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
