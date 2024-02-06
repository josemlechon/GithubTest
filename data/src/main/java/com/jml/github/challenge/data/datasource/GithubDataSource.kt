package com.jml.github.challenge.data.datasource

import com.jml.github.challenge.data.datasource.models.RepositoryDetailsResponse
import com.jml.github.challenge.data.datasource.models.RepositoryResponse

interface GithubDataSource {
    companion object{
        private const val INITIAL_PAGE = 1
    }

    suspend fun getRepoList(page: Int = INITIAL_PAGE): Result<List<RepositoryResponse>>

    suspend fun getRepoDetail(owner: String, repoName: String): Result<RepositoryDetailsResponse>
}