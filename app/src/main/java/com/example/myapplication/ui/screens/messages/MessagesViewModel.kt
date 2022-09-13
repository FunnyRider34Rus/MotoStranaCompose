package com.example.myapplication.ui.screens.messages

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Error
import com.example.myapplication.common.UIEvent
import com.example.myapplication.database.firebase.*
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
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor() : ViewModel(), UIEvent<MessagesEvent> {

    private val _viewState = MutableLiveData(MessagesViewState())
    val viewState: LiveData<MessagesViewState> = _viewState
    private val stamp = Timestamp(System.currentTimeMillis())
    private val date = Date(stamp.time).toString()
    private val time = Time(stamp.time).toString()

    override fun obtainEvent(event: MessagesEvent) {
        when (event) {
            is MessagesEvent.TabsClicked -> getMessages(event.location)
            is MessagesEvent.SendImageClicked -> writeImage(event.location, event.mediaUrl)
            is MessagesEvent.SendMessageClicked -> writeMessage(event.location, event.text)
            is MessagesEvent.FullImageMode -> fullImageMode(event.mediaUrl)
        }
    }

    private fun fullImageMode(mediaUrl: String?) {
        _viewState.value?.isFullMode = !_viewState.value!!.isFullMode
        _viewState.postValue(_viewState.value?.copy(imageUri = mediaUrl))
    }

    private fun writeMessage(location: String?, text: String) {
        val message = Message(
            uid = AUTH.currentUser!!.uid,
            fullname = USER.fullname,
            avatar = USER.avatar,
            text = text,
            date = date,
            time = time,
            mediaUrl = ""
        )
        location?.let {
            REMOTE_DATABASE.child(NODE_COMMON_MESSAGES).child(it).push().setValue(message)
        }
    }

    private fun writeImage(location: String?, mediaUrl: Uri?) {
        val path = location?.let { REMOTE_STORAGE.child(FOLDER_CONTENT_IMAGE).child(it).child(USER.id).child(UUID.randomUUID().toString()) }
        var mediaUrlFromBase: String
        if (mediaUrl != null) {
            path?.putFile(mediaUrl)?.addOnCompleteListener {
                path.downloadUrl.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        mediaUrlFromBase = task.result.toString()
                        val message = Message(
                            uid = AUTH.currentUser!!.uid,
                            fullname = USER.fullname,
                            avatar = USER.avatar,
                            text = "",
                            date = date,
                            time = time,
                            mediaUrl = mediaUrlFromBase
                        )
                        location.let {
                            REMOTE_DATABASE.child(NODE_COMMON_MESSAGES).child(it).push().setValue(message)
                        }
                    }
                }
            }
        }
    }

    private fun getMessages(location: String?) {
        _viewState.value?.isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            var listMessages: List<Message> = listOf()
            location?.let { REMOTE_DATABASE.child(NODE_COMMON_MESSAGES).child(it).orderByChild("stamp")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach { data ->
                            val singleMessage = data.getValue(Message::class.java)
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
        }
        _viewState.value?.isLoading = false
    }
}