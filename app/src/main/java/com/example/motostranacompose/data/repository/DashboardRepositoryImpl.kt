package com.example.motostranacompose.data.repository

import com.example.motostranacompose.core.Constants.FIELD_DASHBOARD_TIMESTAMP
import com.example.motostranacompose.core.Response
import com.example.motostranacompose.core.Response.Failure
import com.example.motostranacompose.core.Response.Success
import com.example.motostranacompose.data.model.DashboardContent
import com.example.motostranacompose.domain.repository.DashboardContentRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepositoryImpl @Inject constructor(private val contentRef: CollectionReference) : DashboardContentRepository {
    override suspend fun getAll(): Flow<Response<List<DashboardContent>>> = callbackFlow {
        val snapshotListener = contentRef.orderBy(FIELD_DASHBOARD_TIMESTAMP, Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            val response = if (value != null) {
                val content = value.toObjects(DashboardContent::class.java)
                Success(content)
            } else {
                Failure(error)
            }
            trySend(response)
        }
        awaitClose { snapshotListener.remove() }
    }

    override suspend fun getByKet(key: String?): Flow<Response<DashboardContent>> = callbackFlow {
        val listener = contentRef.document("$key")
            listener.get().addOnCompleteListener { task ->
                val response = if (task.isSuccessful) {
                    Success(task.result.toObject(DashboardContent::class.java))
                } else {
                    Failure(task.exception)
                }
                trySend(response)
            }
        awaitClose {  }
    }

    override suspend fun insert(data: DashboardContent): Response<Boolean> {
        return try {
            val key = contentRef.document().id
            val mData = data.copy(key = key)
            contentRef.document(key).set(mData).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun update(data: DashboardContent): Response<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(key: String?): Response<Boolean> {
        return try {
            key?.let { contentRef.document(it).delete().await() }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}