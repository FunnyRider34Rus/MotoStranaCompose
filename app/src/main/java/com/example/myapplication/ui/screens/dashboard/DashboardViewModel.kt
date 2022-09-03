package com.example.myapplication.ui.screens.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.EventHandler
import com.example.myapplication.database.NODE_EVENT
import com.example.myapplication.database.NODE_NEWS
import com.example.myapplication.database.REMOTE_DATABASE
import com.example.myapplication.models.Event
import com.example.myapplication.ui.screens.dashboard.model.DashboardEvent
import com.example.myapplication.ui.screens.dashboard.model.DashboardViewState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel(), EventHandler<DashboardEvent> {

    private val _viewState = MutableLiveData(DashboardViewState())
    val viewState: LiveData<DashboardViewState> = _viewState

    override fun obtainEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.NewsClicked -> getEvents(NODE_NEWS)
            DashboardEvent.EventClicked -> getEvents(NODE_EVENT)
            is DashboardEvent.ItemClicked -> getEvent(event.index)
        }
    }

    private fun getEvents(event: String) {
        _viewState.postValue(_viewState.value?.copy(isLoading = true))
        viewModelScope.launch(Dispatchers.IO) {
            var listEvents: List<Event?> = emptyList()
            REMOTE_DATABASE.child(event).child("date")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            val singleEvent = it.getValue(Event::class.java)
                            listEvents = listEvents + singleEvent
                        }
                        _viewState.postValue(_viewState.value?.copy(dashboardValue = listEvents.asReversed()))
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
        _viewState.postValue(_viewState.value?.copy(isLoading = false))
    }

    private fun getEvent(index: Int) {
        _viewState.postValue(
            _viewState.value?.copy(
                itemValue = _viewState.value?.dashboardValue?.get(
                    index
                )
            )
        )
    }
}

