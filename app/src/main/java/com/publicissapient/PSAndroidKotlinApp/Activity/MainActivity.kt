package com.publicissapient.PSAndroidKotlinApp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.publicissapient.PSAndroidKotlinApp.Adapter.NewsAdapter
import com.publicissapient.PSAndroidKotlinApp.ViewModel.NewsListViewModel
import com.publicissapient.PSAndroidKotlinApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val fruits = listOf<String>("mango", "banana", "pineapple", "orange","watermelon")
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel :NewsListViewModel
    private val newsAdapter = NewsAdapter(arrayListOf())
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        printFruitsArray()
        viewModel= ViewModelProviders.of(this).get(NewsListViewModel::class.java)
        viewModel.refresh()
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.list.observe(this, Observer {
            news -> news?.let { newsAdapter.setNewsList(it)
            Log.e(TAG,"SET NEWS LIST CALLED!")
            }
        })
        viewModel.loading.observe(this, Observer { loading -> loading?.let{
            println("Loading")
        } })
        viewModel.errorMessage.observe(this, Observer { errorMessage -> errorMessage?.let{
            println("errorMessage")
        } })
    }

    private fun printFruitsArray() {
        for (item in fruits){
            println("Current Fruit is: $item")
        }
        //To get index
        for(index in fruits.indices){
            println("Current Fruit is : ${fruits[index]}")
        }
    }

}