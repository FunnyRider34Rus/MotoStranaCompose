package com.example.motostranacompose.data.model

data class User(
    var id: String? = null,
    var displayName: String? = null,
    var photoURL: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var status: OnlineStatus? = null
)

enum class OnlineStatus {
    ONLINE, OFFLINE
}
