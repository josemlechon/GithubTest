package com.jml.github.challenge.data.datasource

import com.jml.github.challenge.data.datasource.models.RepositoryDetailsResponse
import com.jml.github.challenge.data.datasource.models.RepositoryResponse
import com.jml.github.challenge.data.datasource.service.GithubService
import com.jml.github.challenge.data.extension.onResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


class GithubDataSourceImpl(
    private val githubService: GithubService,
    private val dispatcher: CoroutineDispatcher,
) : GithubDataSource {

    override suspend fun getRepoList(page: Int): Result<List<RepositoryResponse>> =
        withContext(dispatcher) {

            try {
                githubService.getRepos(query = "stars:>1", sort = "stars", page = page)
                    .onResponse(
                        success = { response ->
                            Result.success(response.repos)
                        },
                        fail = {
                            Result.failure(Exception("Server error  returned"))
                        }
                    )
            } catch (e: Exception) {
                Result.failure(Exception("Unexpected error"))
            }
        }

    override suspend fun getRepoDetail(
        owner: String,
        repoName: String,
    ): Result<RepositoryDetailsResponse> = withContext(dispatcher) {
        try {
            githubService.getRepoDetail(owner = owner, repo = repoName)
                .onResponse(
                    success = { response ->
                        Result.success(response)
                    },
                    fail = {
                        Result.failure(Exception("Server call failing"))
                    }
                )

        } catch (e: Exception) {
            Result.failure(Exception("Unexpected error"))
        }
    }
}