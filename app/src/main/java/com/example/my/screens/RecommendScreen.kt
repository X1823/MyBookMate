package com.example.my.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my.api.RecommendationViewModel
import com.example.my.api.RecommendedBook
import com.example.my.ui.theme.Purple80

@Composable
fun RecommendScreen(modifier: Modifier = Modifier) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val viewModel: RecommendationViewModel = viewModel()
    val context = LocalContext.current
    var refreshTrigger by remember { mutableStateOf(0) }

    LaunchedEffect(refreshTrigger) {
        viewModel.fetchBooks()
    }

    val books by viewModel.books.collectAsState(initial = emptyList())

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                val url = "https://openlibrary.org"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            "Book Recommendations",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                refreshTrigger++
                Toast.makeText(context, "Refreshing...", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple80,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Refresh")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(books) { book ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            book.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = textColor
                        )
                        Text(
                            "by ${book.author}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = textColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            book.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = textColor
                        )
                    }
                }
            }
        }
    }
}
