package com.app.zuludin.newsstand.di.module

import android.content.Context
import androidx.room.Room
import com.app.zuludin.newsstand.data.source.local.room.BookmarkDao
import com.app.zuludin.newsstand.data.source.local.room.NewsDao
import com.app.zuludin.newsstand.data.source.local.room.NewsStandDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseModule(@ApplicationContext context: Context): NewsStandDatabase {
        return Room
            .databaseBuilder(context, NewsStandDatabase::class.java, "newsstand.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBookmarkDao(db: NewsStandDatabase): BookmarkDao = db.bookmarkDao()

    @Provides
    fun provideNewsDao(db: NewsStandDatabase): NewsDao = db.newsDao()
}