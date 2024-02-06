package com.jml.github.challenge.data.datasource.models

import com.google.gson.annotations.SerializedName

class RepositoriesWrapperResponse(
   @SerializedName("total_count") val totalCount: Long,
   @SerializedName("incomplete_results") val resultsIncomplete: Boolean? = false,
   @SerializedName("items") val repos: List<RepositoryResponse>
)