package com.example.myapplication.database.firebase

import com.example.myapplication.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var REMOTE_DATABASE: DatabaseReference
lateinit var REMOTE_STORAGE: StorageReference
lateinit var USER: User

const val NODE_USERS = "users"
const val NODE_NEWS = "news"
const val NODE_EVENT = "event"
const val NODE_PHONES = "phones"
const val NODE_NICKNAMES = "usernames"
const val NODE_COMMON_MESSAGES = "common_messages"

const val FOLDER_PROFILE_AVATAR = "profile_image"
const val FOLDER_CONTENT_IMAGE = "content_image"

const val CHILD_ID = "id"
const val CHILD_FULLNAME = "fullname"
const val CHILD_USERNAME = "username"
const val CHILD_PHONE = "phone"
const val CHILD_BIKE = "bike"
const val CHILD_AVATAR = "avatar"
const val CHILD_STATUS = "status"