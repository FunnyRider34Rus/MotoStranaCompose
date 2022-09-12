package com.example.myapplication.ui.screens.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Error
import com.example.myapplication.common.UIEvent
import com.example.myapplication.database.firebase.AUTH
import com.example.myapplication.database.firebase.NODE_COMMON_MESSAGES
import com.example.myapplication.database.firebase.REMOTE_DATABASE
import com.example.myapplication.database.firebase.USER
import com.example.myapplication.models.Message
import com.example.myapplication.ui.screens.messages.model.MessagesEvent
import com.example.myapplication.ui.screens.messages.model.MessagesViewState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor() : ViewModel(), UIEvent<MessagesEvent> {

    private val _viewState = MutableLiveData(MessagesViewState())
    val viewState: LiveData<MessagesViewState> = _viewState

    override fun obtainEvent(event: MessagesEvent) {
        when (event) {
            is MessagesEvent.TabsClicked -> event.location?.let { getMessages(it) }
            is MessagesEvent.SendMessagesClicked -> writeMessage(event.location, event.text, event.mediaUrl)
            is MessagesEvent.SendMediaClicked -> writeMessage(event.location, event.text, event.mediaUrl)

        }
    }

    private fun writeMessage(location: String?, text: String, mediaUrl: String) {
        val stamp = Timestamp(System.currentTimeMillis())
        val date = Date(stamp.time).toString()
        val time = Time(stamp.time).toString()
        val message = Message(
            uid = AUTH.currentUser!!.uid,
            fullname = USER.fullname,
            logo = USER.avatar,
            text = text,
            date = date,
            time = time,
            mediaUrl = ""
        )
        location?.let {
            REMOTE_DATABASE.child(NODE_COMMON_MESSAGES).child(it).push().setValue(message)
        }
    }

    private fun getMessages(location: String) {
        _viewState.value?.isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            var listMessages: List<Message> = listOf()
            REMOTE_DATABASE.child(NODE_COMMON_MESSAGES).child(location).orderByChild("stamp")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            val singleMessage = it.getValue(Message::class.java)
                            listMessages = listMessages + singleMessage!!
                        }
                        _viewState.value?.isError = Error.NONE
                        _viewState.postValue(_viewState.value?.copy(messages = listMessages))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        _viewState.value?.isError = Error.DB_ERROR
                    }

                })
        }
        _viewState.value?.isLoading = false
    }
}