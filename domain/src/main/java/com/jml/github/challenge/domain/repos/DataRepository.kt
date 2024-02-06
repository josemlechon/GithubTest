package com.jml.github.challenge.domain.repos

import com.jml.github.challenge.domain.entities.Repository
import com.jml.github.challenge.domain.entities.RepositoryDetails
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    suspend fun getRepos() :Result<Unit>

     fun subscribeRepos(): Flow<List<Repository>>

     suspend fun getRepoDetail(id: Long) : Result<RepositoryDetails>
}