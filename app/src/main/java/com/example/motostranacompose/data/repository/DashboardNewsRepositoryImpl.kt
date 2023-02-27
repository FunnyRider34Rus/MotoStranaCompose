package com.example.motostranacompose.data.repository

import com.example.motostranacompose.core.Constants.FIRESTORE_DASHBOARD_KEY
import com.example.motostranacompose.core.Constants.FIRESTORE_NODE_DASHBOARD
import com.example.motostranacompose.core.Constants.FIRESTORE_NODE_NEWS
import com.example.motostranacompose.data.model.DashboardContent.News
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DashboardNewsRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    DashboardNewsRepository {
    override suspend fun get(): Flow<List<News>?> = callbackFlow {
        val listener = firestore.collection(FIRESTORE_NODE_DASHBOARD).document(
            FIRESTORE_DASHBOARD_KEY).collection(
            FIRESTORE_NODE_NEWS).addSnapshotListener { snapshot, e ->
            if (e != null) {
                cancel(e.message.toString())
            } else {
                trySendBlocking(snapshot?.toObjects(News::class.java))
            }
        }
        awaitClose {
            listener.remove()
        }
    }

    override suspend fun insert(news: News) {
        TODO("Not yet implemented")
    }

    override suspend fun update(news: News) {
        news.key?.let {
            firestore.collection(FIRESTORE_NODE_DASHBOARD).document(FIRESTORE_DASHBOARD_KEY).collection(
                FIRESTORE_NODE_NEWS).document(it).set(news, SetOptions.merge())
        }
    }

    override suspend fun delete(news: News) {
        news.key?.let {
            firestore.collection(FIRESTORE_NODE_DASHBOARD).document(FIRESTORE_DASHBOARD_KEY).collection(
                FIRESTORE_NODE_NEWS).document(it).delete()
        }
    }
}