package com.jml.github.challenge.data.models

data class PagerDto (private var currentPage: Int= 0){
    fun increment(){
        currentPage++
    }

    fun nextPage(): Int = currentPage
}