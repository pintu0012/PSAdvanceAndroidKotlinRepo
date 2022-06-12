package com.publicissapient.PSAndroidKotlinApp.di

import com.publicissapient.PSAndroidKotlinApp.Network.NewsApi
import com.publicissapient.PSAndroidKotlinApp.Network.NewsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    val baseUrl ="https://inshorts.deta.dev/"

    @Provides
    fun provideNewsApi(): NewsApi {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    fun provideNewsService() : NewsService{
        return  NewsService()
    }
}