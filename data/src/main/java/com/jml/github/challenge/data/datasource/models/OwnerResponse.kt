package com.jml.github.challenge.data.datasource.models

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("id")val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("login") val owner : String
)