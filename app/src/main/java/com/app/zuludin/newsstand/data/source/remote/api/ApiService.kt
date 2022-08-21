package com.app.zuludin.newsstand.data.source.remote.api

import com.app.zuludin.newsstand.data.source.remote.response.NewsResponse
import com.app.zuludin.newsstand.data.source.remote.response.SourceNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun loadHeadlinesNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("everything")
    suspend fun loadMoreNews(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("top-headlines")
    suspend fun loadNewsByCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("everything")
    suspend fun loadNewsBySources(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("top-headlines/sources")
    suspend fun loadSourcesByCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): SourceNewsResponse
}