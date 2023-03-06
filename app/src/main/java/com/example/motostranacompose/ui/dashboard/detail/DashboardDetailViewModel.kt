package com.example.motostranacompose.ui.dashboard.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motostranacompose.core.Response
import com.example.motostranacompose.domain.use_case.dashboard.DashboardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardDetailViewModel @Inject constructor(private val useCases: DashboardUseCases): ViewModel(), DashboardDetailEvent {

    private val _viewState = MutableStateFlow(DashboardDetailViewState())
    val viewState: StateFlow<DashboardDetailViewState> = _viewState

    private fun getDashboardContentByKey(key: String?) = viewModelScope.launch {
        useCases.getByKey.invoke(key).collect { response ->
            when (response) {
                is Response.Loading -> {
                    _viewState.value = _viewState.value.copy(isLoading = true)
                }
                is Response.Success -> {
                    _viewState.value = _viewState.value.copy( isLoading = false, content = response.data)
                }
                is Response.Failure -> {
                    _viewState.value = _viewState.value.copy(
                        error = response.e?.message ?: "An unexpected error occurred"
                    )
                }
            }
        }
    }

    fun getEvent(event: DashboardDetailEvent) {
        when (event) {
            is DashboardDetailEvent.getContentByKey -> {
                getDashboardContentByKey(event.key)
            }
        }
    }
}