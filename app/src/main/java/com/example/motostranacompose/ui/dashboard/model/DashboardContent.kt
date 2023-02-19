package com.example.motostranacompose.ui.dashboard.model

import com.google.firebase.Timestamp

sealed class DashboardContent {
    abstract val key: String?
    abstract var timestamp: Timestamp?
    abstract var likes: List<String>?
    data class News(
        override val key: String? = null,
        override var timestamp: Timestamp? = null,
        val title: String? = null,
        val header: String? = null,
        val body: String? = null,
        val image: String? = null,
        override var likes: List<String>? = null,
        val type: String? = null
    ) : DashboardContent()

    data class Post(
        override val key: String? = null,
        override var timestamp: Timestamp? = null,
        val id: String? = null,
        val title: String? = null,
        val header: String? = null,
        val body: String? = null,
        val image: String? = null,
        override var likes: List<String>? = null,
        val type: String? = null
    ) : DashboardContent()
}