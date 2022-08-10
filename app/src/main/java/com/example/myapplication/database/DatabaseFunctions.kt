package com.example.myapplication.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REMOTE_DATABASE = FirebaseDatabase.getInstance().reference
}