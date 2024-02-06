package com.jml.github.challenge.domain.entities

data class RepositoryDetails(
    val idRepo: Long,
    val nameRepo: String,
    val description: String,
    val stars: Int,
    val owner: String,
    val urlWebPage : String,
    val forks : Int,
    val language :String
)