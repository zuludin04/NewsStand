package com.app.zuludin.newsstand.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.zuludin.newsstand.data.Resource
import com.app.zuludin.newsstand.domain.model.HomeNews
import com.app.zuludin.newsstand.domain.repository.INewsStandRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MainNewsUiState {
    val isLoading: Boolean
    val errorMessages: List<String>

    data class NoNews(
        override val isLoading: Boolean,
        override val errorMessages: List<String>
    ) : MainNewsUiState

    data class HasNews(
        val news: HomeNews,
        override val isLoading: Boolean,
        override val errorMessages: List<String>
    ) : MainNewsUiState
}

private data class MainViewModelState(
    val news: HomeNews? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList()
) {
    fun toUiState(): MainNewsUiState {
        return if (news == null) {
            MainNewsUiState.NoNews(
                isLoading = isLoading,
                errorMessages = errorMessages,
            )
        } else {
            MainNewsUiState.HasNews(
                news = news,
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        }
    }
}

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: INewsStandRepository) :
    ViewModel() {
    private val viewModelState = MutableStateFlow(MainViewModelState(isLoading = false))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = repository.loadHomeNews().first()
            viewModelState.update {
                when (result) {
                    is Resource.Error -> it.copy(
                        errorMessages = listOf(result.message!!),
                        isLoading = false
                    )
                    is Resource.Loading -> it.copy(isLoading = true)
                    is Resource.Success -> it.copy(news = result.data, isLoading = false)
                }
            }
        }
    }
}