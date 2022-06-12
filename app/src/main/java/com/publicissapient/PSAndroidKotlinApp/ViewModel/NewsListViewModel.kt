package com.publicissapient.PSAndroidKotlinApp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.publicissapient.PSAndroidKotlinApp.Model.News
import com.publicissapient.PSAndroidKotlinApp.Model.NewsModelClass
import com.publicissapient.PSAndroidKotlinApp.Network.ApiService
import com.publicissapient.PSAndroidKotlinApp.Network.RetroInstance
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class NewsListViewModel : ViewModel() {

    val list = MutableLiveData<List<News>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    val service = RetroInstance.getInstance().create<ApiService>()


    fun refresh() {
//        getNewsList()
    fetchNewsList()
    }

    private fun fetchNewsList(){
        loading.value=true
        disposable.add(
            service.getNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<NewsModelClass>(){
                    override fun onSuccess(value: NewsModelClass) {
                        list.value =value.data
                        loading.value=false
                        errorMessage.value=null
                    }

                    override fun onError(e: Throwable?) {
                        loading.value=false
                        errorMessage.value= e?.message
                    }

                })
        )
    }

    private fun getNewsList() {
        val service = RetroInstance.getInstance().create<ApiService>()
        val response = service.getNewsList()
        response.enqueue(object : Callback<NewsModelClass> {
            override fun onResponse(
                call: Call<NewsModelClass>,
                response: Response<NewsModelClass>
            ) {
                loading.postValue(false)
                list.postValue(response.body()?.data ?: arrayListOf())
                println(response.body()?.data.toString())
            }

            override fun onFailure(call: Call<NewsModelClass>, t: Throwable) {
                loading.postValue(false)
                errorMessage.postValue(t.message)
            }
        }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}