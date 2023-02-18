package com.example.motostranacompose.model

data class User(
    var id: String? = null,
    val isAnonymous: Boolean = true,
    var name: String? = null,
    var avatar: String? = null,
    var lat: Double? = null,
    var lon: Double? = null,
    var status: Status? = null
)

enum class Status {
    ONLINE, OFFLINE
}
