package com.publicissapient.PSAndroidKotlinApp.di

import com.publicissapient.PSAndroidKotlinApp.Network.NewsService
import com.publicissapient.PSAndroidKotlinApp.ViewModel.NewsListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service:NewsService)
    fun inject(viewModel: NewsListViewModel)
}