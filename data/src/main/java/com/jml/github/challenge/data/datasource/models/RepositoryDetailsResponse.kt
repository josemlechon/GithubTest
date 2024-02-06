package com.jml.github.challenge.data.datasource.models

import com.google.gson.annotations.SerializedName

data class RepositoryDetailsResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    @SerializedName("language") val language: String? = null,
    @SerializedName("description") val description: String?,
    @SerializedName("owner")val owner: OwnerResponse? = null,
    @SerializedName("html_url")val url: String,
)

