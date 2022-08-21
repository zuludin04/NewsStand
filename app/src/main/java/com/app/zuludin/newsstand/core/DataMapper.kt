package com.app.zuludin.newsstand.core

import com.app.zuludin.newsstand.data.source.local.entity.BookmarkEntity
import com.app.zuludin.newsstand.data.source.local.entity.NewsEntity
import com.app.zuludin.newsstand.data.source.remote.response.ArticlesItem
import com.app.zuludin.newsstand.data.source.remote.response.SourcesItem
import com.app.zuludin.newsstand.domain.model.News
import com.app.zuludin.newsstand.domain.model.NewsSource

object DataMapper {
    fun mapNewsResponseToEntity(news: List<ArticlesItem>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()

        news.map {
            val n = NewsEntity(
                title = it.title!!,
                author = it.author ?: "",
                image = it.urlToImage!!,
                url = it.url!!,
                description = it.description!!,
                source = it.source?.name!!,
                content = it.content!!,
                id = it.publishedAt!!
            )
            newsList.add(n)
        }

        return newsList
    }

    fun mapNewsEntityToModel(news: List<NewsEntity>): List<News> = news.map {
        News(
            title = it.title,
            publish = it.id,
            author = it.author,
            image = it.image,
            url = it.url,
            description = it.description,
            source = it.source,
            content = it.content
        )
    }

    fun mapNewsModelToEntity(news: News): BookmarkEntity =
        BookmarkEntity(
            title = news.title,
            publish = news.publish,
            author = news.author,
            image = news.image,
            url = news.url,
            description = news.description,
            source = news.source,
            content = news.content
        )

    fun mapBookmarkEntityToModel(news: List<BookmarkEntity>): List<News> = news.map {
        News(
            title = it.title,
            publish = it.publish,
            author = it.author,
            image = it.image,
            url = it.url,
            description = it.description,
            source = it.source,
            content = it.content
        )
    }

    fun mapNewsResponseToModel(news: List<ArticlesItem>): List<News> {
        val newsList = ArrayList<News>()

        news.map {
            val n = News(
                title = it.title!!,
                author = it.author ?: "",
                image = it.urlToImage!!,
                url = it.url!!,
                description = it.description!!,
                source = it.source?.name!!,
                content = it.content!!,
                publish = it.publishedAt!!
            )
            newsList.add(n)
        }

        return newsList
    }

    fun mapSourceResponseToModel(sources: List<SourcesItem>): List<NewsSource> {
        val sourceList = ArrayList<NewsSource>()

        sources.map {
            val s = NewsSource(name = it.name!!, id = it.id!!)
            sourceList.add(s)
        }

        return sourceList
    }
}