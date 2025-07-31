package com.example.my.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendationApi {
    @GET("search.json")
    suspend fun getBooks(@Query("q") query: String): BookSearchResponse
}
