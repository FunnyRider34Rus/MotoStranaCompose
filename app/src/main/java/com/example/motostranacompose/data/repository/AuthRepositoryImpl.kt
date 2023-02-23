package com.example.motostranacompose.data.repository

import com.example.motostranacompose.core.Constants.CREATED_AT
import com.example.motostranacompose.core.Constants.DISPLAY_NAME
import com.example.motostranacompose.core.Constants.ID
import com.example.motostranacompose.core.Constants.PHOTO_URL
import com.example.motostranacompose.core.Constants.SIGN_IN_REQUEST
import com.example.motostranacompose.core.Constants.SIGN_UP_REQUEST
import com.example.motostranacompose.core.Constants.USERS
import com.example.motostranacompose.data.model.Response.Failure
import com.example.motostranacompose.data.model.Response.Success
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
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

    override suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse {
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
                firestore.collection(USERS).document(uid).delete().await()
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
            firestore.collection(USERS).document(uid).set(user).await()
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    ID to uid,
    DISPLAY_NAME to displayName,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to FieldValue.serverTimestamp()
)