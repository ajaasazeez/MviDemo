package com.example.mymviapp.ui.main

import androidx.lifecycle.*
import com.example.mymviapp.databse.DataAccessObject
import com.example.mymviapp.model.NewsModel
import com.example.mymviapp.repository.MainRepository
import com.example.mymviapp.ui.main.state.MainStateEvent
import com.example.mymviapp.ui.main.state.MainViewState
import com.example.mymviapp.utils.AbsentLiveData
import com.example.mymviapp.utils.DataState

class MainViewModel (val repository: MainRepository): ViewModel() {


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

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
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

    fun getCurrentViewStateOrNew(): MainViewState {
        val value = viewState.value?.let {
            it
        } ?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent) {
        val state: MainStateEvent
        state = event
        _stateEvent.value = state
    }

    class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}