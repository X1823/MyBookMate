package com.example.my
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.my.screens.LibraryScreen
import com.example.my.screens.RecommendScreen
import com.example.my.screens.MyPageScreen
import com.example.my.ui.theme.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bookDao = AppDatabase.getDatabase(applicationContext).bookDao()
        val repository = BookRepository(bookDao)
        val factory = BookViewModelFactory(repository)
        val bookViewModel = ViewModelProvider(this, factory)[BookViewModel::class.java]

        var isDarkMode by mutableStateOf(false)

        setContent {
            MyTheme(darkTheme = isDarkMode) {
                var selectedIndex by remember { mutableStateOf(0) }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Book, contentDescription = "Library") },
                                label = { Text("Library") },
                                selected = selectedIndex == 0,
                                onClick = { selectedIndex = 0 }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Recommend") },
                                label = { Text("Recommend") },
                                selected = selectedIndex == 1,
                                onClick = { selectedIndex = 1 }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Person, contentDescription = "My Page") },
                                label = { Text("My Page") },
                                selected = selectedIndex == 2,
                                onClick = { selectedIndex = 2 }
                            )
                        }
                    }
                ) { innerPadding ->
                    when (selectedIndex) {
                        0 -> LibraryScreen(
                            modifier = Modifier.padding(innerPadding),
                            bookViewModel = bookViewModel
                        )
                        1 -> RecommendScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                        2 -> MyPageScreen(
                            modifier = Modifier.padding(innerPadding),
                            bookViewModel = bookViewModel,
                            isDarkMode = isDarkMode,
                            onToggleTheme = { isDarkMode = !isDarkMode }
                        )
                    }
                }
            }
        }
    }
}