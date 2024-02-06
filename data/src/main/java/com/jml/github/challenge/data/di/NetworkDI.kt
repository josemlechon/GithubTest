package com.jml.github.challenge.data.di

import com.jml.github.challenge.data.datasource.service.GithubService
import com.jml.github.challenge.data.network.NetworkClient
import org.koin.dsl.module
import retrofit2.Retrofit

val network = module {

    single {
        NetworkClient.getOkHttp()
    }

    single {
        NetworkClient.getBuild()
    }

    single {
        get<Retrofit>().create(GithubService::class.java)
    }

}