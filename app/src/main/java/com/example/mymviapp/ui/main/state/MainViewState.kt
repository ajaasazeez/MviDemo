package com.example.mymviapp.ui.main.state
import com.example.mymviapp.model.NewsModel

data class MainViewState(
    var news: List<NewsModel>? = null
)