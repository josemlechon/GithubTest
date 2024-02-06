package com.jml.github.challenge.domain.entities

data class Repository(
    val idRepo: Long,
    val nameRepo: String,
    val description: String,
    val stars: Int,
    val owner: String,
    val language: String
)