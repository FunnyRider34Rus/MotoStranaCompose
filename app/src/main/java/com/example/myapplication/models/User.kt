package com.example.myapplication.models

data class User(
    val id: String = "",
    var fullname:String = "Имя Фамилия",
    var username: String = "",
    var phone: String = "",
    var bike: String = "",
    var avatar: String = "",
    var status: String = ""
)
