package com.jml.github.challenge.ui.vm.state

import com.jml.github.challenge.ui.models.RepoDetailUI

sealed interface DetailState {
    class Error(val type: DetailErrorType) : DetailState
    object Loading : DetailState
    class Data(val item: RepoDetailUI) : DetailState
}


enum class DetailErrorType{
    INVALID_ID,
    GENERIC
}