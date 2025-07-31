package com.example.my.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecommendationRepository {
    private val api: RecommendationApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openlibrary.org/") // 保持不变
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RecommendationApi::class.java)
    }

    suspend fun getRecommendations(query: String): List<RecommendedBook> {
        val response = api.getBooks(query)
        return response.docs.mapNotNull { doc ->
            val title = doc.title ?: return@mapNotNull null
            val author = doc.author_name?.firstOrNull() ?: "Unknown Author"
            val description = doc.first_sentence?.firstOrNull() ?: "No description available."
            RecommendedBook(title, author, description)
        }
    }
}

