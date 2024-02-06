package com.jml.github.challenge.data.testdata

import com.jml.github.challenge.data.datasource.models.RepositoryResponse
import io.mockk.mockk
import kotlin.random.Random

object RepoTestData {

    fun validRepos(): List<RepositoryResponse> {
        return (1..10).map {
            RepositoryResponse(
                id = it.toLong(),
                nameRepo = "Repo Name $it",
                description = "Description $it",
                owner = mockk(relaxed = true),
                language = "COBOL",
                stars = Random.nextInt()
            )
        }
    }

    fun reposWithMissingLanguage(): List<RepositoryResponse> {
        return (1..10).map {
            RepositoryResponse(
                id = it.toLong(),
                nameRepo = "Repo Name $it",
                description = "Description $it",
                owner = mockk(relaxed = true),
                language = if (it % 2 == 0) "COBOL" else null,
                stars = Random.nextInt()
            )
        }
    }
}