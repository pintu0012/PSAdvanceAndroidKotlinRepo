package com.publicissapient.PSAndroidKotlinApp.Network

import com.publicissapient.PSAndroidKotlinApp.ViewModel.NewsListViewModel
import io.reactivex.Single
import retrofit2.http.GET

interface NewsApi {
    @GET("https://inshorts.deta.dev/news?category=all")
    fun getNews(): Single<NewsListViewModel>
}