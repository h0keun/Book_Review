package com.com.book_review

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.com.book_review.dao.HistoryDao
import com.com.book_review.dao.ReviewDao
import com.com.book_review.model.History
import com.com.book_review.model.Review

@Database(entities = [History::class, Review::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun reviewDao(): ReviewDao
}

fun getAppDatabase(context: Context): AppDatabase {
    val migration_1_2 = object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase){
            database.execSQL("CREATE TABLE 'REVIEW'('id' INTEGER, 'review' Text," + "PRIMARY KEY('id'))")
        }
    }

    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "BookSearchDB"
    ).addMigrations(migration_1_2).build()
}