package com.example.my.screens

import android.content.Context
import android.os.Environment
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.my.BookViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    bookViewModel: BookViewModel,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val books = bookViewModel.allBooks.value.orEmpty()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Username: Ryan", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Total Books Read: ${books.size}")
        Text("Current Reading Goals: 2025 Reading Plan", textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                exportReadingData(context, books)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Export Reading Data")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                scope.launch {
                    bookViewModel.deleteAllBooks()  // ✅ 修复调用正确方法名
                }
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear Reading History")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onToggleTheme,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode")
        }
    }
}

fun exportReadingData(context: Context, books: List<com.example.my.Book>) {
    val fileName = "reading_data.csv"
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)

    try {
        val outputStream = FileOutputStream(file)
        outputStream.write("Title,Author,Progress,Note\n".toByteArray())

        for (book in books) {
            val line = "${book.title},${book.author},${book.progress},${book.note ?: ""}\n"
            outputStream.write(line.toByteArray())
        }

        outputStream.close()
        println("Reading data exported to ${file.absolutePath}")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}




