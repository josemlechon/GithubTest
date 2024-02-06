package com.jml.github.challenge.data.datasource

import com.jml.github.challenge.data.datasource.models.RepositoryDetailsResponse
import com.jml.github.challenge.data.datasource.models.RepositoryResponse
import com.jml.github.challenge.data.datasource.service.GithubService
import com.jml.github.challenge.data.network.HeadersInterceptor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
internal class GithubDataSourceTest {

    lateinit var mockWebServer: MockWebServer

    @BeforeEach
    fun before() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @AfterEach
    fun after() {
        mockWebServer.shutdown()
    }

    @Test
    fun `request repos contains Token and Version as headers`() = test {
        runTest {
            `Given a request returns`(200, validRepoFile)

            `When getting repos`()

            `Then there should be valid headers`()
        }
    }

    @Test
    fun `request detail contains Token and Version as headers`() = test {
        runTest {
            `Given a request returns`(200, validDetailFile)

            `When getting detail repo`()

            `Then there should be valid headers`()
        }
    }

    @Test
    fun `request repos should return a list of repos response`()= test {
        runTest {
            `Given a request returns`(200, validRepoFile)

            `When getting repos`()

            `Then a list of valid repos returned`()
        }
    }


    @Test
    fun `request repos should return failure when there is errors in the data returned`()= test {
        runTest {
            `Given a request returns`(400, invalidFile)

            `When getting repos`()

            `Then a failure is returned instead of repos`()
        }
    }

    private fun `Given a request returns`(statusCode: Int = 200, fileName: String) {
        val body = this::class.java.classLoader?.getResource(fileName)?.readText().orEmpty()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(statusCode)
                .setBody(body)
        )
    }

    private suspend fun TestScope.`When getting repos`() {

        resultRepos = sut.getRepoList(page = 0)
    }


    private suspend fun TestScope.`When getting detail repo`() {

        resultDetail = sut.getRepoDetail(owner = owner, repoName = repo)
    }

    private fun TestScope.`Then there should be valid headers`() {

        val request = mockWebServer.takeRequest()
        val tokenParamValue =  request.headers["Authorization"]
        Assertions.assertEquals("Bearer $apiKeyValue", tokenParamValue)

        val apiVersion = request.headers["X-GitHub-Api-Version"]
        Assertions.assertEquals(apiVersion, versionAPI)
    }

    private fun TestScope.`Then a list of valid repos returned`(){

        Assertions.assertEquals(true, resultRepos?.isSuccess)
        Assertions.assertEquals(30, resultRepos?.getOrNull()?.size)
    }

    private fun TestScope.`Then a failure is returned instead of repos`(){

        Assertions.assertEquals(true, resultRepos?.isFailure)
    }

    inner class TestScope {

        val apiKeyValue: String = "${Random.nextInt()}"
        val versionAPI = "2022-11-28"
        val owner = "an_owner"
        val repo = "a_repo"

        val validRepoFile = "valid_repo_query.json"
        val validDetailFile = "valid_detail_query.json"

        val invalidFile = "invalid_query.json"


        var resultRepos: Result<List<RepositoryResponse>>? = null
        var resultDetail: Result<RepositoryDetailsResponse>? = null

        private val baseUrl = mockWebServer.url("/").toString()

        private val api: GithubService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(HeadersInterceptor(apiKeyValue)).build()
            )
            .build()
            .create(GithubService::class.java)


        val sut: GithubDataSource by lazy {
            GithubDataSourceImpl(api, UnconfinedTestDispatcher())
        }
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }
}