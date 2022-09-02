package com.example.myapplication.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var AUTH: FirebaseAuth
lateinit var REMOTE_DATABASE: DatabaseReference
const val NODE_USERS = "users"
const val NODE_NEWS = "news"
const val NODE_EVENT = "event"
const val NODE_PHONES = "phones"
const val CHILD_ID = "id"
const val CHILD_FULLNAME = "fullname"
const val CHILD_USERNAME = "username"
const val CHILD_PHONE = "phone"
const val CHILD_BIO = "bio"
const val CHILD_AVATAR = "avatar"
const val CHILD_STATUS = "status"