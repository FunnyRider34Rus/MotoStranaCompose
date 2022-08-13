package com.example.myapplication.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REMOTE_DATABASE = Firebase.database("https://motostranacompose-1917b-default-rtdb.europe-west1.firebasedatabase.app/").reference
}