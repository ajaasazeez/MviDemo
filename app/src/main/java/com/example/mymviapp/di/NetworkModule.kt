package com.example.mymviapp.di

import android.app.Application
import androidx.room.Room
import com.example.mymviapp.api.ApiService
import com.example.mymviapp.databse.DataAccessObject
import com.example.mymviapp.databse.NewsDatabase
import com.example.mymviapp.repository.MainRepository
import com.example.mymviapp.ui.main.MainViewModel
import com.example.mymviapp.utils.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    fun provideRetrofit(
        converterFactory: Converter.Factory,
        httpClient: OkHttpClient,
        url: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()

    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create(GsonBuilder().setLenient().create())

    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
        return okHttpClientBuilder.build()
    }

    fun api(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    factory {
        provideRetrofit(
            get(), get(), "https://api.spaceflightnewsapi.net/v3/"
        )
    }
    factory { provideConverterFactory() }

    single { provideHttpClient() }
    single { api(get()) }


}