package com.example.mymviapp.api
import androidx.lifecycle.LiveData
import com.example.mymviapp.model.NewsModel
import com.example.mymviapp.utils.GenericApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("articles")
    fun getNews(): LiveData<GenericApiResponse<List<NewsModel>>>


}