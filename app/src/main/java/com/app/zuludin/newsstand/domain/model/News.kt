package com.app.zuludin.newsstand.domain.model

data class News(
    val title: String,
    val publish: String,
    val author: String,
    val image: String,
    val url: String,
    val description: String,
    val source: String,
    val content: String
)
