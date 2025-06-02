package com.example.my.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MyPageScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // User avatar placeholder
        Surface(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            // Placeholder avatar (you can later replace with Image)
            Box(contentAlignment = Alignment.Center) {
                Text("U", style = MaterialTheme.typography.headlineLarge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User name
        Text("Username: Ryan", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // Reading stats
        Text("Total Books Read: 12", style = MaterialTheme.typography.bodyLarge)
        Text("Current Reading Goals: 2025 Reading Plan", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(24.dp))

        // Settings options (placeholder buttons)
        Button(
            onClick = { /* TODO: Implement export data */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Export Reading Data")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { /* TODO: Implement clear history */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear Reading History")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { /* TODO: Implement dark mode toggle */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Toggle Dark Mode")
        }
    }
}




