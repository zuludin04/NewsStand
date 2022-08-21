package com.app.zuludin.newsstand.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo
    var title: String,

    @ColumnInfo
    var author: String,

    @ColumnInfo
    var publish: String,

    @ColumnInfo
    var image: String
)
