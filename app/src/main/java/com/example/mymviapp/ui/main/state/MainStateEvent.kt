package com.example.mymviapp.ui.main.state

sealed class MainStateEvent {
    object GetNewsEvent : MainStateEvent()
    object None : MainStateEvent()
}