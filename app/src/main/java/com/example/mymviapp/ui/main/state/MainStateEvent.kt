package com.example.mymviapp.ui.main.state

sealed class MainStateEvent {
    class GetNewsEvent : MainStateEvent()

    class None: MainStateEvent()
}