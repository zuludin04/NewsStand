package com.app.zuludin.newsstand.domain.model

data class HomeNews(
    val hottest: List<News>,
    val more: List<News>
)