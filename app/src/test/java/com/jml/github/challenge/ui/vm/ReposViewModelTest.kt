package com.jml.github.challenge.ui.vm

import com.jml.github.challenge.ui.vm.state.ReposState
import com.jml.github.challenge.ui.vm.testdata.DataRepositoryMock
import com.jml.github.challenge.ui.vm.testdata.UIViewModelTestData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ReposViewModelTest {

    @Test
    fun `requesting data returns a list of UI Repo data`() = test {

        `Giving a request of data`()

        `When requesting data`()

        `Then we get a list of data to paint`()
    }

    @Test
    fun `requesting should ask the UI to show loading state`() = test {

        `Giving a request of data capturing everything emitted`()

        `When requesting data`()

        `Then loading state is emitted`()
    }


    private fun TestScope.`Giving a request of data`() {
        dataRepository.setListDataToEmmit(UIViewModelTestData.getValidDomainRepos())
    }

    private suspend fun TestScope.`Giving a request of data capturing everything emitted`() {
        dataRepository.setListDataToEmmit(UIViewModelTestData.getValidDomainRepos())
        sut.viewState.toList(statesEmitted)
    }

    private fun TestScope.`When requesting data`() {
        sut.requestData()
    }

    private suspend fun TestScope.`Then we get a list of data to paint`() {
        val result = sut.viewState.first() as ReposState.Data
        Assertions.assertTrue(result.listItems.isNotEmpty())
    }

    private fun TestScope.`Then loading state is emitted`() {
        Assertions.assertTrue(statesEmitted.first() is ReposState.Loading)
    }

    private class TestScope {

        val dataRepository = DataRepositoryMock()

        val statesEmitted = mutableListOf<ReposState>()

        val sut: ReposViewModel by lazy {
            ReposViewModel(dataRepository)
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun test(block: suspend TestScope.() -> Unit) = runTest {

        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        TestScope().block()
    }
}