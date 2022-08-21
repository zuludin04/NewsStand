package com.app.zuludin.newsstand.di

import com.app.zuludin.newsstand.domain.repository.INewsStandRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MapsModuleDependencies {

    fun newsRepository(): INewsStandRepository
}