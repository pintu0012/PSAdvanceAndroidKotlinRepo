package com.publicissapient.PSAndroidKotlinApp.Network

import com.publicissapient.PSAndroidKotlinApp.Model.NewsModelClass
import com.publicissapient.PSAndroidKotlinApp.ViewModel.NewsListViewModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("https://inshorts.deta.dev/news?category=all")
    fun getNewsList() : Call<NewsModelClass>
    @GET("https://inshorts.deta.dev/news?category=all")
    fun getNews(): Single<NewsModelClass>

//    companion object {
//        var retrofitService: RetrofitService? = null
//
//        fun getInstance() : RetrofitService {
//            if (retrofitService == null) {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl("https://howtodoandroid.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                retrofitService = retrofit.create(RetrofitService::class.java)
//            }
//            return retrofitService!!
//        }
//    }

}