package com.jml.github.challenge.data.datasource.models

import com.google.gson.annotations.SerializedName

class RepositoryResponse(
    @SerializedName("id")val id : Long,
    @SerializedName("name") val nameRepo: String,
    @SerializedName("description") val description : String?= null,
    @SerializedName("owner") val owner: OwnerResponse,
    @SerializedName("language") val language: String? =null,
    @SerializedName("stargazers_count") val stars : Int
)