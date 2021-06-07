package com.example.mymviapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymviapp.model.NewsModel

@Dao
interface DataAccessObject {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addNews(subscriber: List<NewsModel>)

    @Query("SELECT * FROM NewsModel")
     fun getAllNews(): List<NewsModel>
}