package com.example.myapplication.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH: FirebaseAuth
lateinit var REMOTE_DATABASE: DatabaseReference
const val NODE_USERS = "users"