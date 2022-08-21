package com.app.zuludin.newsstand.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "newsId")
    var id: String,

    @ColumnInfo
    var title: String,

    @ColumnInfo
    var author: String,

    @ColumnInfo
    var image: String
)