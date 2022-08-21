package com.app.zuludin.newsstand.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.zuludin.newsstand.data.source.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark")
    fun loadBookmarkNews(): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmarkNews(entity: BookmarkEntity)

    @Query("DELETE FROM bookmark WHERE id = :id")
    fun deleteBookmark(id: Int)
}