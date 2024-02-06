package com.jml.github.challenge.ui.vm.state

import com.jml.github.challenge.ui.models.RepositoryUI

sealed interface ReposState {
    object None : ReposState
    object Error : ReposState
    object Loading : ReposState
    class Data(val listItems: List<RepositoryUI>) : ReposState
}