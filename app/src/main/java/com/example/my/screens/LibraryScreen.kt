package com.example.my.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.my.Book
import com.example.my.BookViewModel
import com.example.my.ui.theme.Pink80
import com.example.my.ui.theme.Purple80
import com.example.my.ui.theme.PurpleGrey80
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    bookViewModel: BookViewModel
) {
    val bookList by bookViewModel.allBooks.observeAsState(initial = emptyList())

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var progress by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title", color = Color.DarkGray) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurpleGrey80,
                unfocusedBorderColor = PurpleGrey80,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = PurpleGrey80
            ),
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author", color = PurpleGrey80) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurpleGrey80,
                unfocusedBorderColor = PurpleGrey80
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = progress,
            onValueChange = { progress = it },
            label = { Text("Progress", color = PurpleGrey80) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurpleGrey80,
                unfocusedBorderColor = PurpleGrey80
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note", color = PurpleGrey80) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurpleGrey80,
                unfocusedBorderColor = PurpleGrey80
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                bookViewModel.addBook(
                    Book(
                        title = title,
                        author = author,
                        progress = progress.toIntOrNull() ?: 0,
                        note = note
                    )
                )
                title = ""
                author = ""
                progress = ""
                note = ""
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple80,
                contentColor = Color.White
            ),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Book")
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        bookList.forEach { book ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text("Title: ${book.title}")
                Text("Author: ${book.author}")
                Text("Progress: ${book.progress}%")
                Text("Note: ${book.note}")
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
