package com.jml.github.challenge.di

import com.jml.github.challenge.data.datasource.GithubDataSource
import com.jml.github.challenge.data.datasource.GithubDataSourceImpl
import com.jml.github.challenge.data.datasource.cache.MemoryCache
import com.jml.github.challenge.data.repository.DataRepositoryImpl
import com.jml.github.challenge.domain.entities.Repository
import com.jml.github.challenge.domain.repos.DataRepository
import com.jml.github.challenge.ui.vm.DetailViewModel
import com.jml.github.challenge.ui.vm.ReposViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ReposViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val dataModule = module {

    single { MemoryCache<Repository>() }

    single<GithubDataSource> { GithubDataSourceImpl(get(), Dispatchers.IO) }

    single<DataRepository> { DataRepositoryImpl(get(), get()) }
}