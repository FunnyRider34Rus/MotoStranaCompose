package com.example.motostranacompose.data.model

import com.google.type.LatLng

data class CurrentUser(
    val uid: String? = null,
    val displayName: String? = null,
    val photoURL: String? = null,
    val userLatLng: LatLng? = null,
    val userStatus: UserOnlineStatus = UserOnlineStatus.OFFLINE
)

enum class UserOnlineStatus {
    OFFLINE, ONLINE
}