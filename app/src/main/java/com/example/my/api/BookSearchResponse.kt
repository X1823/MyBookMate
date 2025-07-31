package com.example.my.api

data class BookSearchResponse(
    val docs: List<BookDoc>
)

data class BookDoc(
    val title: String?,
    val author_name: List<String>?,
    val first_sentence: List<String>?
)
