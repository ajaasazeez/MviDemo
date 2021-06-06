package com.example.mymviapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mymviapp.api.RetrofitBuilder
import com.example.mymviapp.model.NewsModel
import com.example.mymviapp.ui.main.state.MainViewState
import com.example.mymviapp.utils.*

object MainRepository {

    fun getNews(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<NewsModel>, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<NewsModel>>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        news = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<NewsModel>>> {
                return RetrofitBuilder.apiService.getNews()
            }

        }.asLiveData()
    }
}