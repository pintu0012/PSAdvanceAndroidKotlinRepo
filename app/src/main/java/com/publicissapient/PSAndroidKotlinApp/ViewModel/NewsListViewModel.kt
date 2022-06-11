package com.publicissapient.PSAndroidKotlinApp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.publicissapient.PSAndroidKotlinApp.Model.NewsModelClass
import com.publicissapient.PSAndroidKotlinApp.Network.ApiService
import com.publicissapient.PSAndroidKotlinApp.Network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class NewsListViewModel : ViewModel() {

    val list = MutableLiveData<List<NewsModelClass>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


    fun refresh() {
        getNewsList()
    }

    private fun getNewsList() {
        val service = RetroInstance.getInstance().create<ApiService>()
        val response = service.getNewsList()
        response.enqueue(object : Callback<List<NewsModelClass>> {
            override fun onResponse(
                call: Call<List<NewsModelClass>>,
                response: Response<List<NewsModelClass>>
            ) {
                loading.postValue(false)
                list.postValue(response.body())
            }

            override fun onFailure(call: Call<List<NewsModelClass>>, t: Throwable) {
                loading.postValue(false)
                errorMessage.postValue(t.message)
            }
        }
        )
    }

}