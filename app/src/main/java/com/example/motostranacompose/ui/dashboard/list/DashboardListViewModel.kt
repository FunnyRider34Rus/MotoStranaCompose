package com.example.motostranacompose.ui.dashboard.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motostranacompose.core.EventHandler
import com.example.motostranacompose.domain.use_case.GetDashboardContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardListViewModel @Inject constructor(private val getDashboardContent: GetDashboardContentUseCase) :
    ViewModel(),
    EventHandler<DashboardListEvent> {

    private val _viewState = MutableStateFlow(DashboardListViewState())
    val viewState: StateFlow<DashboardListViewState> = _viewState

    init {
        viewModelScope.launch {
            _viewState.value.content = getDashboardContent.invoke()
        }
    }

    override fun obtainEvent(event: DashboardListEvent) {
        when (event) {
            DashboardListEvent.ClickLike -> {}
            DashboardListEvent.ClickComment -> {}
            DashboardListEvent.ClickShare -> {
                TODO("deeplink")
            }
        }
    }


}