package com.app.zuludin.newsstand.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.zuludin.newsstand.data.source.local.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class NewsStandDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}