package com.app.zuludin.newsstand.data.source.local

import com.app.zuludin.newsstand.data.source.local.entity.BookmarkEntity
import com.app.zuludin.newsstand.data.source.local.entity.NewsEntity
import com.app.zuludin.newsstand.data.source.local.room.BookmarkDao
import com.app.zuludin.newsstand.data.source.local.room.NewsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsStandLocalSource @Inject constructor(
    private val bookmarkDao: BookmarkDao,
    private val newsDao: NewsDao
) {

    fun loadNewsCache() = newsDao.loadNewsCache()

    suspend fun saveNews(news: List<NewsEntity>) = newsDao.addNewsCache(news)

    fun loadBookmarkNews() = bookmarkDao.loadBookmarkNews()

    fun addBookmark(entity: BookmarkEntity) = bookmarkDao.addBookmarkNews(entity)

    fun deleteBookmark(id: Int) = bookmarkDao.deleteBookmark(id)

}