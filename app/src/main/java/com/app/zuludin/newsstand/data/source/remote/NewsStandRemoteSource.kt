package com.app.zuludin.newsstand.data.source.remote

import com.app.zuludin.newsstand.data.source.remote.api.ApiResponse
import com.app.zuludin.newsstand.data.source.remote.api.ApiService
import com.app.zuludin.newsstand.data.source.remote.response.ArticlesItem
import com.app.zuludin.newsstand.data.source.remote.response.SourcesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsStandRemoteSource @Inject constructor(private val api: ApiService) {
    suspend fun loadHeadlines(): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = api.loadHeadlinesNews("us", "2b7a8553686741318e5d3c40754e55dd")
                val news = response.articles!!
                if (news.isNotEmpty()) {
                    emit(ApiResponse.Success(news))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadMoreNews(): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = api.loadMoreNews(
                    "techcrunch.com,thenextweb.com",
                    "2b7a8553686741318e5d3c40754e55dd"
                )
                val news = response.articles!!
                if (news.isNotEmpty()) {
                    emit(ApiResponse.Success(news))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadNewsByCategory(category: String): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = api.loadNewsByCategory(category, "2b7a8553686741318e5d3c40754e55dd")
                val news = response.articles!!
                if (news.isNotEmpty()) {
                    emit(ApiResponse.Success(news))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadNewsBySources(sources: String): Flow<ApiResponse<List<ArticlesItem>>> {
        return flow {
            try {
                val response = api.loadNewsBySources(
                    sources,
                    "2b7a8553686741318e5d3c40754e55dd"
                )
                val news = response.articles!!
                if (news.isNotEmpty()) {
                    emit(ApiResponse.Success(news))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadSourcesByCategory(category: String): Flow<ApiResponse<List<SourcesItem>>> {
        return flow {
            try {
                val response =
                    api.loadSourcesByCategory(category, "2b7a8553686741318e5d3c40754e55dd")
                val news = response.sources!!
                if (news.isNotEmpty()) {
                    emit(ApiResponse.Success(news))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}