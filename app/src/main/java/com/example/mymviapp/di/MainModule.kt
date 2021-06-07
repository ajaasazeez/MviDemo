package com.example.mymviapp.di

import com.example.mymviapp.repository.MainRepository
import com.example.mymviapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { MainRepository(get(), get()) }
    viewModel { MainViewModel(get()) }
}