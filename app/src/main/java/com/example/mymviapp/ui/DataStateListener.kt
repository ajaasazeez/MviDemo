package com.example.mymviapp.ui

import com.example.mymviapp.utils.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}