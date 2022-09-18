package com.example.myapplication.common

import com.example.myapplication.database.firebase.AUTH
import com.example.myapplication.database.firebase.NODE_USERS
import com.example.myapplication.database.firebase.REMOTE_DATABASE
import com.example.myapplication.database.firebase.USER
import com.example.myapplication.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

fun initUser() {
    val currentUID = AUTH.currentUser?.uid
    if (currentUID != null) {
    REMOTE_DATABASE.child(NODE_USERS).child(currentUID.toString())
        .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                USER =
                    snapshot.getValue(User::class.java) ?: User()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}