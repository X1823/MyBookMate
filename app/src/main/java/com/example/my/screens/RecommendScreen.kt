package com.example.my.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class RecommendedBook(val title: String, val author: String, val description: String)

val recommendations = listOf(
    RecommendedBook("One Hundred Years of Solitude", "Gabriel García Márquez", "The loneliness and fate of seven generations of people in Macondo are intertwined, showing the charm of magical realism."),
    RecommendedBook("The Red and the Black", "Stendhal", "Young Julien struggles between love and power, reflecting the social contradictions in France in the 19th century."),
    RecommendedBook("The Narrow Gate", "André Gide", "A story full of abstinence and idealized love, exploring the conflict between soul and desire."),
    RecommendedBook("The Kite Runner", "Khaled Hosseini", "A touching story about redemption and friendship, spanning the war in Afghanistan and the suffering of life.")
)

@Composable
fun RecommendScreen(modifier: Modifier = Modifier) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            "Book Recommendations",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(recommendations) { book ->
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

