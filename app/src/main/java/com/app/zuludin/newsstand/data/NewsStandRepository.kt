package com.app.zuludin.newsstand.data

import com.app.zuludin.newsstand.core.AppExecutors
import com.app.zuludin.newsstand.core.DataMapper
import com.app.zuludin.newsstand.data.source.local.NewsStandLocalSource
import com.app.zuludin.newsstand.data.source.remote.NewsStandRemoteSource
import com.app.zuludin.newsstand.data.source.remote.api.ApiResponse
import com.app.zuludin.newsstand.data.source.remote.response.ArticlesItem
import com.app.zuludin.newsstand.domain.model.News
import com.app.zuludin.newsstand.domain.model.NewsSource
import com.app.zuludin.newsstand.domain.repository.INewsStandRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@Singleton
class NewsStandRepository @Inject constructor(
    private val remoteSource: NewsStandRemoteSource,
    private val localSource: NewsStandLocalSource,
    private val appExecutors: AppExecutors
) : INewsStandRepository {
    override fun loadHeadlines(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<ArticlesItem>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localSource.loadNewsCache().map {
                    DataMapper.mapNewsEntityToModel(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItem>>> =
                remoteSource.loadHeadlines()

            override suspend fun saveCallResult(data: List<ArticlesItem>) {
                val news = DataMapper.mapNewsResponseToEntity(data)
                localSource.saveNews(news)
            }
        }.asFlow()

    override fun loadMoreNews(): Flow<Resource<List<News>>> {
        return flow {
            val apiResponse = remoteSource.loadMoreNews().first()

            if (apiResponse is ApiResponse.Success) {
                val news = apiResponse.data
                val result = Resource.Success(DataMapper.mapNewsResponseToModel(news))
                emitAll(flowOf(result))
            } else if (apiResponse is ApiResponse.Error) {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun loadNewsByCategory(category: String): Flow<Resource<List<News>>> {
        return flow {
            val apiResponse = remoteSource.loadNewsByCategory(category).first()

            if (apiResponse is ApiResponse.Success) {
                val news = apiResponse.data
                val result = Resource.Success(DataMapper.mapNewsResponseToModel(news))
                emitAll(flowOf(result))
            } else if (apiResponse is ApiResponse.Error) {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun loadNewsBySources(sources: String): Flow<Resource<List<News>>> {
        return flow {
            val apiResponse = remoteSource.loadNewsBySources(sources).first()

            if (apiResponse is ApiResponse.Success) {
                val news = apiResponse.data
                val result = Resource.Success(DataMapper.mapNewsResponseToModel(news))
                emitAll(flowOf(result))
            } else if (apiResponse is ApiResponse.Error) {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun loadSources(category: String): Flow<Resource<List<NewsSource>>> {
        return flow {
            val apiResponse = remoteSource.loadSourcesByCategory(category).first()

            if (apiResponse is ApiResponse.Success) {
                val sources = apiResponse.data
                val result = Resource.Success(DataMapper.mapSourceResponseToModel(sources))
                emitAll(flowOf(result))
            } else if (apiResponse is ApiResponse.Error) {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    override fun loadBookmarkNews(): Flow<List<News>> {
        return localSource.loadBookmarkNews().map {
            DataMapper.mapBookmarkEntityToModel(it)
        }
    }

    override fun addBookmarkNews(news: News) {
        val n = DataMapper.mapNewsModelToEntity(news)
        appExecutors.diskIO().execute {
            localSource.addBookmark(n)
        }
    }

    override fun deleteBookmarkNews(id: Int) {
        appExecutors.diskIO().execute {
            localSource.deleteBookmark(id)
        }
    }
}