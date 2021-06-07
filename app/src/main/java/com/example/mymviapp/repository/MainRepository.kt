package com.example.mymviapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mymviapp.api.ApiService

import com.example.mymviapp.database.DataAccessObject
import com.example.mymviapp.model.NewsModel
import com.example.mymviapp.ui.main.state.MainViewState
import com.example.mymviapp.utils.ApiSuccessResponse
import com.example.mymviapp.utils.DataState
import com.example.mymviapp.utils.GenericApiResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainRepository(val newsDao: DataAccessObject,val apiService: ApiService) {

    fun getNews(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<NewsModel>, MainViewState>() {

            override fun createCall(): LiveData<GenericApiResponse<List<NewsModel>>> {
                return apiService.getNews()
            }

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<NewsModel>>) {
                //caching the response
                GlobalScope.launch {
                    insert(response.body)
                }

                result.postValue(
                    DataState.data(
                        null,
                        MainViewState(
                            news = response.body
                        )
                    )
                )
            }

            override fun handleApiErrorResponse(message: String) {
                GlobalScope.launch {
                    //set data from cache on network failure
                    result.postValue(
                        DataState.data(
                            null,
                            MainViewState(news = newsDao.getAllNews())
                        )
                    )
                }
            }

        }.asLiveData()
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(newsModel: List<NewsModel>) {
        newsDao.addNews(newsModel)
    }

}