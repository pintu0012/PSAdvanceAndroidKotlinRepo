package com.publicissapient.PSAndroidKotlinApp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.publicissapient.PSAndroidKotlinApp.Model.News
import com.publicissapient.PSAndroidKotlinApp.Model.NewsModelClass
import com.publicissapient.PSAndroidKotlinApp.Network.NewsService
import com.publicissapient.PSAndroidKotlinApp.di.DaggerApiComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListViewModel : ViewModel() {

    @Inject
    lateinit var service:NewsService

    val list = MutableLiveData<List<News>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    init {
        DaggerApiComponent.create().inject(this)
    }


    fun refresh() {
    fetchNewsList()
    }

    private fun fetchNewsList(){
        loading.value=true
        disposable.add(
            service.getNewsList()
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
                        errorMessage.value= true
                    }

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}