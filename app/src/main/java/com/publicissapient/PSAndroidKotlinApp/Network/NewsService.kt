package com.publicissapient.PSAndroidKotlinApp.Network

import com.publicissapient.PSAndroidKotlinApp.Model.NewsModelClass
import com.publicissapient.PSAndroidKotlinApp.di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NewsService {

    @Inject
    lateinit var api: NewsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getNewsList():Single<NewsModelClass>{
        return api.getNews()
    }
}