package com.jml.github.challenge.data.repository

import com.jml.github.challenge.data.datasource.GithubDataSource
import com.jml.github.challenge.data.datasource.cache.MemoryCache
import com.jml.github.challenge.data.datasource.models.RepositoryResponse
import com.jml.github.challenge.data.mapper.DataReposMapper
import com.jml.github.challenge.data.models.PagerDto
import com.jml.github.challenge.domain.entities.Repository
import com.jml.github.challenge.domain.entities.RepositoryDetails
import com.jml.github.challenge.domain.repos.DataRepository
import kotlinx.coroutines.flow.Flow

class DataRepositoryImpl(
    private val githubDS: GithubDataSource,
    private val cacheRepos: MemoryCache<Repository>
) : DataRepository {

    private val page: PagerDto = PagerDto(0)

    override suspend fun getRepos(): Result<Unit> {

        return githubDS.getRepoList(page = page.nextPage())
            .fold(onSuccess = {

                storeRepos(it)
                page.increment()
                Result.success(Unit)

            }, onFailure = {
                Result.failure(it)
            })
    }

    private fun storeRepos(list: List<RepositoryResponse>) {
        list.filterNot { it.language == null }
            .let(DataReposMapper::mapListReposToDomain)
            .let(cacheRepos::addItems)
    }


    override fun subscribeRepos(): Flow<List<Repository>> {
        return cacheRepos.observeChanges()
    }

    override suspend fun getRepoDetail(id: Long): Result<RepositoryDetails> {
        return cacheRepos.getCurrentData().firstOrNull { it.idRepo == id }
            ?.let { repo ->
                githubDS.getRepoDetail(repo.owner, repo.nameRepo).fold(
                    onSuccess = {
                        val repoDetail = DataReposMapper.mapRepoDetailToDomain(it)
                        Result.success(repoDetail)
                    },
                    onFailure = {
                        Result.failure(it)
                    }
                )
            } ?: Result.failure(Exception("Item not found"))

    }
}