package com.example.mymviapp.di

import android.app.Application
import androidx.room.Room
import com.example.mymviapp.databse.DataAccessObject
import com.example.mymviapp.databse.NewsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, "news_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideNewsDao(database: NewsDatabase): DataAccessObject {
        return database.dataAccessObject()
    }

    single { provideDatabase(androidApplication()) }
    single { provideNewsDao(get()) }
}