package com.app.zuludin.newsstand.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.zuludin.newsstand.domain.repository.INewsStandRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: INewsStandRepository) : ViewModel() {
    val news = repository.loadHeadlines().asLiveData()
}