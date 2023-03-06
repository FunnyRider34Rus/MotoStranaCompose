package com.example.motostranacompose.data.repository

import com.example.motostranacompose.core.Constants.FIELD_USER_CREATED_AT
import com.example.motostranacompose.core.Constants.FIELD_USER_DISPLAY_NAME
import com.example.motostranacompose.core.Constants.FIELD_USER_LATLNG
import com.example.motostranacompose.core.Constants.FIELD_USER_PHOTO_URL
import com.example.motostranacompose.core.Constants.FIELD_USER_STATUS
import com.example.motostranacompose.core.Constants.FIELD_USER_UID
import com.example.motostranacompose.core.Constants.FIRESTORE_NODE_USERS
import com.example.motostranacompose.core.Constants.SIGN_IN_REQUEST
import com.example.motostranacompose.core.Constants.SIGN_UP_REQUEST
import com.example.motostranacompose.core.Response.Failure
import com.example.motostranacompose.core.Response.Success
import com.example.motostranacompose.data.model.UserOnlineStatus
import com.example.motostranacompose.domain.repository.*
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private var signInClient: GoogleSignInClient,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Success(signUpResult)
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponse {
        return try {
            auth.currentUser?.apply {
                firestore.collection(FIRESTORE_NODE_USERS).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private suspend fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            firestore.collection(FIRESTORE_NODE_USERS).document(uid).set(user).await()
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    FIELD_USER_UID to uid,
    FIELD_USER_DISPLAY_NAME to displayName,
    FIELD_USER_PHOTO_URL to photoUrl?.toString(),
    FIELD_USER_CREATED_AT to serverTimestamp(),
    FIELD_USER_LATLNG to null,
    FIELD_USER_STATUS to UserOnlineStatus.OFFLINE
)