package com.jml.github.challenge.data.datasource.service

import com.jml.github.challenge.data.datasource.models.RepositoriesWrapperResponse
import com.jml.github.challenge.data.datasource.models.RepositoryDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("/search/repositories")
    suspend fun getRepos(
        @Query("q")  query :String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Response<RepositoriesWrapperResponse>


    @GET("repos/{owner}/{repo}")
    suspend fun getRepoDetail(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<RepositoryDetailsResponse>

}