package com.jml.github.challenge.ui.vm.testdata

import com.jml.github.challenge.domain.entities.Repository
import kotlin.random.Random

object UIViewModelTestData {

    fun getValidDomainRepos(): List<Repository> {
        return (1..10).map {
            Repository(
                idRepo = it.toLong(),
                nameRepo = "Repo Name $it",
                description = "Description $it",
                stars = Random.nextInt(),
                owner = "owner $it",
                language = "language $it"
            )
        }
    }
}