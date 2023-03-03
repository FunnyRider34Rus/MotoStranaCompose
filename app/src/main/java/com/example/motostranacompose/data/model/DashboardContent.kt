package com.example.motostranacompose.data.model

import com.google.firebase.Timestamp

data class DashboardContent(
    val key: String? = null,
    val timestamp: Timestamp? = null,
    val uid: String? = null,
    val title: String? = null,
    val header: String? = null,
    val body: String? = null,
    val image: String? = null,
    val likes: List<String>? = null,
    val type: String? = null
)

enum class DashboardType {
    NEWS, POST
}
