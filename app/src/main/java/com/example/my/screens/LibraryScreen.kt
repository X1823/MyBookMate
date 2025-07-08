package com.example.my.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my.Book
import com.example.my.BookViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LibraryScreen(
    bookViewModel: BookViewModel,
    modifier: Modifier = Modifier
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var showDetailDialog by remember { mutableStateOf(false) }
    var selectedBook by remember { mutableStateOf<Book?>(null) }

    var newTitle by remember { mutableStateOf("") }
    var newAuthor by remember { mutableStateOf("") }
    var newProgress by remember { mutableStateOf(0f) }

    val books by bookViewModel.books.collectAsState()

    Box(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Column {
            Text("My Library", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

            Button(onClick = { showAddDialog = true }) {
                Text("Add Book")
            }

            Spacer(Modifier.height(16.dp))

            books.forEach { book ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            selectedBook = book
                            newProgress = book.progress
                            showDetailDialog = true
                        },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFECEFF1))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Title: ${book.title}", fontWeight = FontWeight.Bold)
                        Text("Author: ${book.author}")
                        Text("Progress: ${book.progress}%")
                    }
                }
            }
        }

        // 添加书籍对话框
        if (showAddDialog) {
            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                confirmButton = {
                    Button(onClick = {
                        bookViewModel.addBook(
                            Book(title = newTitle, author = newAuthor, progress = newProgress)
                        )
                        showAddDialog = false
                        newTitle = ""
                        newAuthor = ""
                        newProgress = 0f
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Add Book") },
                text = {
                    Column {
                        OutlinedTextField(value = newTitle, onValueChange = { newTitle = it }, label = { Text("Title") })
                        OutlinedTextField(value = newAuthor, onValueChange = { newAuthor = it }, label = { Text("Author") })
                        OutlinedTextField(value = newProgress.toString(), onValueChange = {
                            newProgress = it.toFloatOrNull() ?: 0f
                        }, label = { Text("Progress (%)") })
                    }
                }
            )
        }

        // 详情对话框
        if (showDetailDialog && selectedBook != null) {
            AlertDialog(
                onDismissRequest = { showDetailDialog = false },
                confirmButton = {
                    Button(onClick = {
                        bookViewModel.updateProgress(selectedBook!!, newProgress)
                        showDetailDialog = false
                    }) {
                        Text("Update")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        bookViewModel.deleteBook(selectedBook!!)
                        showDetailDialog = false
                    }) {
                        Text("Delete")
                    }
                },
                title = { Text("Book Details") },
                text = {
                    Column {
                        Text("Title: ${selectedBook!!.title}")
                        Text("Author: ${selectedBook!!.author}")
                        OutlinedTextField(
                            value = newProgress.toString(),
                            onValueChange = { newProgress = it.toFloatOrNull() ?: 0f },
                            label = { Text("Progress (%)") }
                        )
                    }
                }
            )
        }
    }
}
