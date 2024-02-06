package com.jml.github.challenge.ui.vm.testdata

import com.jml.github.challenge.domain.entities.Repository
import com.jml.github.challenge.domain.entities.RepositoryDetails
import com.jml.github.challenge.domain.repos.DataRepository
import io.mockk.mockk
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class DataRepositoryMock : DataRepository {


    private val dataFlow = MutableSharedFlow<List<Repository>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private var repoListDataToEmmit : List<Repository>? = null

    fun setListDataToEmmit(data : List<Repository>) {
        repoListDataToEmmit = data
    }

    override suspend fun getRepos(): Result<Unit> {
        dataFlow.tryEmit(repoListDataToEmmit.orEmpty())
        return Result.success(Unit)
    }

    override fun subscribeRepos(): Flow<List<Repository>> = dataFlow

    override suspend fun getRepoDetail(id: Long): Result<RepositoryDetails> {
        return Result.success( mockk(relaxed = true))
    }
}