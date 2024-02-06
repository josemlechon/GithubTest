package com.jml.github.challenge.data.repository

import com.jml.github.challenge.data.datasource.GithubDataSource
import com.jml.github.challenge.data.datasource.cache.MemoryCache
import com.jml.github.challenge.data.testdata.RepoTestData
import com.jml.github.challenge.domain.entities.Repository
import com.jml.github.challenge.domain.repos.DataRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class DataRepositoryTest {

    @Test
    fun `requesting repos saves them in the cache and emmit them`() = test {
        runTest {

            `Giving a valid list of repos `()

            `when requesting repos`()

            `Then a list of repos is emitted`()
        }
    }

    @Test
    fun `repos with language null will be discarded`() = test {
        runTest {

            `Giving a list of repos with some of them with null language`()

            `when requesting repos`()

            `Then the nullable language are discarded`()
        }
    }

    @Test
    fun `requesting repos returns failure`() = test {
        runTest {

            `Giving an invalid request to repos`()

            `when requesting repos`()

            `Then a failure from repos is emitted`()
        }
    }

    private fun TestScope.`Giving a valid list of repos `() {
        coEvery { githubDS.getRepoList(any()) } returns Result.success(RepoTestData.validRepos())
    }

    private fun TestScope.`Giving a list of repos with some of them with null language`() {
        coEvery { githubDS.getRepoList(any()) } returns Result.success(RepoTestData.reposWithMissingLanguage())
    }

    private fun TestScope.`Giving an invalid request to repos`() {
        coEvery { githubDS.getRepoList(any()) } returns Result.failure(Exception())
    }

    private suspend fun TestScope.`when requesting repos`() {
        resultRepos =  sut.getRepos()
    }

    private suspend fun TestScope.`Then a list of repos is emitted`() {
        val listRepos = sut.subscribeRepos().firstOrNull()
        Assertions.assertEquals(listRepos, cacheRepos.getCurrentData())
    }

    private fun TestScope.`Then the nullable language are discarded`() {
        Assertions.assertTrue(RepoTestData.reposWithMissingLanguage().size > cacheRepos.getCurrentData().size)
    }

    private fun TestScope.`Then a failure from repos is emitted`() {
        Assertions.assertTrue(resultRepos!!.isFailure)
    }



    inner class TestScope {


        var resultRepos :  Result<Unit>? = null
        val githubDS: GithubDataSource = mockk()
        val cacheRepos = MemoryCache<Repository>()

        val sut: DataRepository by lazy {
            DataRepositoryImpl(githubDS, cacheRepos)
        }

    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }
}