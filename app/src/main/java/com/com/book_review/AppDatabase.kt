package com.com.book_review

import androidx.room.Database
import androidx.room.RoomDatabase
import com.com.book_review.dao.HistoryDao
import com.com.book_review.model.History

@Database(entities = [History::class], version =1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}