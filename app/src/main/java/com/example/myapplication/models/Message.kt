package com.example.myapplication.models

data class Message(
    val uid: String = "",
    var text: String = "",
    var date: String = "",
    var time: String = "",
    var imageUrl: String = "",
    var reply: String = "",
)
