package com.example.motostranacompose.data.repository

import com.example.motostranacompose.core.Constants.FIRESTORE_DASHBOARD_KEY
import com.example.motostranacompose.core.Constants.FIRESTORE_NODE_DASHBOARD
import com.example.motostranacompose.core.Constants.FIRESTORE_NODE_POSTS
import com.example.motostranacompose.data.model.DashboardContent.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DashboardPostsRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    DashboardPostsRepository {
    override suspend fun get(): Flow<List<Post>?> = callbackFlow {
        val listener = firestore.collection(FIRESTORE_NODE_DASHBOARD).document(
            FIRESTORE_DASHBOARD_KEY).collection(
            FIRESTORE_NODE_POSTS
        ).addSnapshotListener { snapshot, e ->
            if (e != null) {
                cancel(e.message.toString())
            } else {
                trySendBlocking(snapshot?.toObjects(Post::class.java))
            }
        }
        awaitClose {
            listener.remove()
        }
    }

    override suspend fun insert(post: Post) {
        TODO("Not yet implemented")
    }

    override suspend fun update(post: Post) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(post: Post) {
        TODO("Not yet implemented")
    }
}