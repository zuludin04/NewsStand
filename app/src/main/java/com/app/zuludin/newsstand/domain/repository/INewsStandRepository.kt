package com.app.zuludin.newsstand.domain.repository

import com.app.zuludin.newsstand.data.Resource
import com.app.zuludin.newsstand.domain.model.News
import com.app.zuludin.newsstand.domain.model.NewsSource
import kotlinx.coroutines.flow.Flow

interface INewsStandRepository {
    fun loadHeadlines(): Flow<Resource<List<News>>>

    fun loadMoreNews(): Flow<Resource<List<News>>>

    fun loadNewsByCategory(category: String): Flow<Resource<List<News>>>

    fun loadNewsBySources(sources: String): Flow<Resource<List<News>>>

    fun loadSources(category: String): Flow<Resource<List<NewsSource>>>

    fun loadBookmarkNews(): Flow<List<News>>

    fun addBookmarkNews(news: News)

    fun deleteBookmarkNews(id: Int)
}