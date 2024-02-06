package com.jml.github.challenge.ui.models.mapper

import com.jml.github.challenge.domain.entities.Repository
import com.jml.github.challenge.domain.entities.RepositoryDetails
import com.jml.github.challenge.ui.models.RepoDetailUI
import com.jml.github.challenge.ui.models.RepositoryUI

object ViewUIMapper {

    fun mapReposToUI(list: List<Repository>): List<RepositoryUI> {
        return list.map {
            RepositoryUI(
                id = it.idRepo,
                name = it.nameRepo,
                description = it.description,
                stars = it.stars
            )
        }
    }

    fun mapRepoDetailDomainToUI(data: RepositoryDetails): RepoDetailUI {
        return RepoDetailUI(
            name = data.nameRepo,
            description = data.description,
            stars = data.stars.toString(),
            forks = data.forks.toString(),
            language = data.language,
            urlWebRepo = data.urlWebPage
        )
    }
}