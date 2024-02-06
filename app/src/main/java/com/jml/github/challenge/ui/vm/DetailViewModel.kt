package com.jml.github.challenge.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jml.github.challenge.domain.repos.DataRepository
import com.jml.github.challenge.ui.models.mapper.ViewUIMapper
import com.jml.github.challenge.ui.vm.state.DetailErrorType
import com.jml.github.challenge.ui.vm.state.DetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val dataRepo: DataRepository) : ViewModel() {

    private val _viewState = MutableStateFlow<DetailState>(DetailState.Loading)
    val viewState: StateFlow<DetailState> = _viewState

    fun getRepoDetails(id: Long?) {

        if (id == null) {
            _viewState.update { DetailState.Error(DetailErrorType.INVALID_ID) }
            return
        }

        viewModelScope.launch {

            _viewState.update { DetailState.Loading }
            dataRepo.getRepoDetail(id)
                .fold(
                    onSuccess = { detail ->
                        val uiModel = ViewUIMapper.mapRepoDetailDomainToUI(detail)
                        _viewState.update { DetailState.Data(uiModel) }
                    },
                    onFailure = {
                        _viewState.update { DetailState.Error(DetailErrorType.GENERIC) }
                    }
                )
        }
    }
}