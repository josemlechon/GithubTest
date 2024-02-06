package com.jml.github.challenge.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jml.github.challenge.domain.repos.DataRepository
import com.jml.github.challenge.ui.models.mapper.ViewUIMapper
import com.jml.github.challenge.ui.vm.state.ReposState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReposViewModel(private val dataRepo: DataRepository) : ViewModel() {

    private val _viewState = MutableStateFlow<ReposState>(ReposState.Loading)
    val viewState: StateFlow<ReposState> = _viewState

    init {
        subscribeData()
        requestData()
    }

    fun requestData() {

        viewModelScope.launch {
            _viewState.update { ReposState.Loading }
            dataRepo.getRepos()
                .fold(
                    onSuccess = {},
                    onFailure = {
                        _viewState.update { ReposState.Error}
                    }
                )
        }
    }

    private fun subscribeData() {

        viewModelScope.launch {
            dataRepo.subscribeRepos()
                .catch {
                    _viewState.update { ReposState.Error}
                }
                .collect { data ->
                    val uiModels = ViewUIMapper.mapReposToUI(data)
                    _viewState.update { ReposState.Data(uiModels) }
                }
        }
    }
}