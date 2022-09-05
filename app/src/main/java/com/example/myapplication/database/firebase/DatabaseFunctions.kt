package com.example.myapplication.database.firebase

import com.example.myapplication.MotoStranaCompose.Companion.applicationContext
import com.example.myapplication.database.AUTH
import com.example.myapplication.database.REMOTE_DATABASE
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

fun initFirebase() {
    FirebaseApp.initializeApp(applicationContext())
    AUTH = FirebaseAuth.getInstance()
    AUTH.setLanguageCode("ru")
    REMOTE_DATABASE = Firebase.database("https://motostranacompose-1917b-default-rtdb.europe-west1.firebasedatabase.app/").reference
}