package com.example.my.api

data class RecommendedBook(
    val title: String,
    val author: String,
    val description: String = "No description available."
)
