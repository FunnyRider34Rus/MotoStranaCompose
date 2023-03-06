package com.example.motostranacompose.ui.dashboard.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motostranacompose.core.Response.*
import com.example.motostranacompose.domain.repository.UserRepository
import com.example.motostranacompose.domain.use_case.dashboard.DashboardUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardListViewModel @Inject constructor(private val useCases: DashboardUseCases, private val userRepository: UserRepository) :
    ViewModel(), DashboardListEvent {

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
                    _viewState.value =
                        _viewState.value.copy(isLoading = false, contents = response.data ?: emptyList())
                }
                is Failure -> {
                    _viewState.value = _viewState.value.copy(
                        error = response.e?.message ?: "An unexpected error occurred"
                    )
                }
            }
        }
    }

    fun getLikeStatus(list: List<String>?): LikeStatus {
        var result: LikeStatus
        result = if (list.isNullOrEmpty())  LikeStatus.NONE else LikeStatus.UNLIKE
        if (list?.contains(userRepository.getCurrentUser().uid) == true) result = LikeStatus.LIKE
        return result
    }

    fun obtainEvent(event: DashboardListEvent) = viewModelScope.launch {
        when (event) {
            is DashboardListEvent.ContentClick -> {}
            is DashboardListEvent.LikeClick -> {
                useCases.likeClick.invoke(event.content)
            }
            is DashboardListEvent.CommentClick -> {}
            is DashboardListEvent.ShareClick -> {}
            is DashboardListEvent.ButtonAddClick -> {}
        }
    }
}