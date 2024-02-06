package com.jml.github.challenge.data.mapper

import com.jml.github.challenge.data.datasource.models.RepositoryDetailsResponse
import com.jml.github.challenge.data.datasource.models.RepositoryResponse
import com.jml.github.challenge.domain.entities.Repository
import com.jml.github.challenge.domain.entities.RepositoryDetails

object DataReposMapper {

    fun mapListReposToDomain(list: List<RepositoryResponse>): List<Repository> {
        return list.map { repoRes ->
            Repository(
                idRepo = repoRes.id,
                nameRepo = repoRes.nameRepo,
                description = repoRes.description.orEmpty(),
                owner = repoRes.owner.owner,
                language = repoRes.language.orEmpty(),
                stars = repoRes.stars
            )
        }
    }

    fun mapRepoDetailToDomain(response: RepositoryDetailsResponse): RepositoryDetails {
        return RepositoryDetails(
            idRepo = response.id,
            nameRepo = response.name,
            stars = response.stargazersCount,
            forks = response.forksCount,
            description = response.description.orEmpty(),
            owner = response.owner?.owner.orEmpty(),
            urlWebPage = response.url,
            language = response.language.orEmpty()
        )
    }
}