package com.example.mymviapp.di

import com.example.mymviapp.api.ApiService
import com.example.mymviapp.utils.Constants
import com.example.mymviapp.utils.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            get(), get(), Constants.BASE_URL
        )
    }
    factory { provideConverterFactory() }
    single { provideHttpClient() }
    single { api(get()) }


}