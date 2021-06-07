package com.example.mymviapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymviapp.model.NewsModel

@Database(entities = [NewsModel::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun dataAccessObject(): DataAccessObject
}
