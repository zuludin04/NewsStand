package com.app.zuludin.newsstand.di.module

import com.app.zuludin.newsstand.data.NewsStandRepository
import com.app.zuludin.newsstand.domain.repository.INewsStandRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [ApiModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(repository: NewsStandRepository): INewsStandRepository
}