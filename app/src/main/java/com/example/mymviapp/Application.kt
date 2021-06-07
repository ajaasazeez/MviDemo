package com.example.mymviapp

import android.app.Application
import com.example.mymviapp.databse.NewsDatabase
import com.example.mymviapp.repository.MainRepository

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { NewsDatabase.getDatabase(this) }
    val repository by lazy { MainRepository(database.newsDao()) }


}