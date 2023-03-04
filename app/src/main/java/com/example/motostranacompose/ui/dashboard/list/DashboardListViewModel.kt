package com.example.motostranacompose.ui.dashboard.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motostranacompose.core.EventHandler
import com.example.motostranacompose.core.Response.*
import com.example.motostranacompose.domain.use_case.dashboard.DashboardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardListViewModel @Inject constructor(private val useCases: DashboardUseCases) :
    ViewModel(), EventHandler<DashboardListEvent> {

    private val _viewState = MutableStateFlow(DashboardListViewState())
    val viewState: StateFlow<DashboardListViewState> = _viewState

    init {
        getAllContent()
    }


    private fun getAllContent() = viewModelScope.launch {
        useCases.getAll().collect { response ->
            when (response) {
                is Loading -> {
                    _viewState.value = _viewState.value.copy(isLoading = true)
                }
                is Success -> {
                    _viewState.value = _viewState.value.copy(isLoading = false)
                    _viewState.value =
                        _viewState.value.copy(contents = response.data ?: emptyList())
                }
                is Failure -> {
                    _viewState.value = _viewState.value.copy(
                        error = response.e?.message ?: "An unexpected error occurred"
                    )
                }
            }
        }
    }

    fun getDashboardContentByKey(key: String) = viewModelScope.launch {
        useCases.getByKey.invoke(key).collect { response ->
            when (response) {
                is Loading -> {
                    _viewState.value = _viewState.value.copy(isLoading = true)
                }
                is Success -> {
                    _viewState.value = _viewState.value.copy(isLoading = false)
                    _viewState.value = _viewState.value.copy(content = response.data)
                }
                is Failure -> {
                    _viewState.value = _viewState.value.copy(
                        error = response.e?.message ?: "An unexpected error occurred"
                    )
                }
            }
        }
    }

    override fun obtainEvent(event: DashboardListEvent) {
        when (event) {
            DashboardListEvent.ContentClick -> {}
            DashboardListEvent.LikeClick -> {}
            DashboardListEvent.CommentClick -> {}
            DashboardListEvent.ShareClick -> {}
        }
    }

}