package com.example.my

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.my.ui.theme.MyTheme
import com.example.my.screens.*

class MainActivity : ComponentActivity() {

    private val bookViewModel: BookViewModel by viewModels {
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = BookRepository(database.bookDao())
        BookViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                var selectedTab by remember { mutableStateOf(0) }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = selectedTab == 0,
                                onClick = { selectedTab = 0 },
                                label = { Text("Library") },
                                icon = { Icon(Icons.Default.Book, contentDescription = null) }
                            )
                            NavigationBarItem(
                                selected = selectedTab == 1,
                                onClick = { selectedTab = 1 },
                                label = { Text("Recommend") },
                                icon = { Icon(Icons.Default.Favorite, contentDescription = null) }
                            )
                            NavigationBarItem(
                                selected = selectedTab == 2,
                                onClick = { selectedTab = 2 },
                                label = { Text("My Page") },
                                icon = { Icon(Icons.Default.Person, contentDescription = null) }
                            )
                        }
                    }
                ) { innerPadding ->
                    when (selectedTab) {
                        0 -> LibraryScreen(bookViewModel, Modifier.padding(innerPadding))
                        1 -> RecommendScreen(Modifier.padding(innerPadding))
                        2 -> MyPageScreen(Modifier.padding(innerPadding))
                    }
                }
            }
        }
    } // 👈 加回来的括号
} // 👈 加回来的括号
