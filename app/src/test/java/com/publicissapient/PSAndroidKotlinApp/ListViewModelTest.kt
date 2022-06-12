package com.publicissapient.PSAndroidKotlinApp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.publicissapient.PSAndroidKotlinApp.Model.News
import com.publicissapient.PSAndroidKotlinApp.Model.NewsModelClass
import com.publicissapient.PSAndroidKotlinApp.Network.NewsService
import com.publicissapient.PSAndroidKotlinApp.ViewModel.NewsListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.http.GET
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var newsService: NewsService

    @InjectMocks
    var newsListViewmodel = NewsListViewModel()

    private var testSingle: Single<NewsModelClass>? =null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getNewsSuccess(){
        val news = News( "author","content","date","id","imageUrl","readMoreUrl","time","url")
        val newsList = arrayListOf(news)
        val  newsmodel = NewsModelClass("category",newsList)

        testSingle = Single.just(newsmodel)
        `when`(newsService.getNewsList()).thenReturn(testSingle)

        newsListViewmodel.refresh()

        Assert.assertEquals(1,newsListViewmodel.list.value?.size)
        Assert.assertEquals(false, newsListViewmodel.errorMessage.value)
        Assert.assertEquals(false, newsListViewmodel.loading.value)
    }

    @Test
    fun getNewsFailure(){
        testSingle = Single.error(Throwable())
        `when`(newsService.getNewsList()).thenReturn(testSingle)

        newsListViewmodel.refresh()

        Assert.assertEquals(true, newsListViewmodel.errorMessage.value)
        Assert.assertEquals(false, newsListViewmodel.loading.value)
    }


    @Before
    fun setUpRxSchedulers(){
        val immediate = object :Scheduler(){
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }
        RxJavaPlugins.setInitIoSchedulerHandler{scheduler -> immediate}
        RxJavaPlugins.setInitComputationSchedulerHandler{scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler->immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler->immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler{scheduler->immediate}
    }
}