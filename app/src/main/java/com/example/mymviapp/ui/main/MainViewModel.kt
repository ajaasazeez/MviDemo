package com.example.mymviapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mymviapp.model.NewsModel
import com.example.mymviapp.repository.MainRepository
import com.example.mymviapp.ui.main.state.MainStateEvent
import com.example.mymviapp.ui.main.state.MainViewState
import com.example.mymviapp.utils.AbsentLiveData
import com.example.mymviapp.utils.DataState

class MainViewModel(private val repository: MainRepository) : ViewModel() {


    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()


    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        return when (stateEvent) {

            is MainStateEvent.GetNewsEvent -> {
                repository.getNews()
            }

            is MainStateEvent.None -> {
                AbsentLiveData.create()
            }
        }
    }

    fun setNews(news: List<NewsModel>) {
        val update = getCurrentViewStateOrNew()
        update.news = news
        _viewState.value = update
    }

    private fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value?.let {
            it
        } ?: MainViewState()
    }

    fun setStateEvent(event: MainStateEvent) {
        val state: MainStateEvent = event
        _stateEvent.value = state
    }

}