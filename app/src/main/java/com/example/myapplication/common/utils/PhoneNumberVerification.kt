package com.example.myapplication.common.utils

import android.content.ContentValues.TAG
import android.util.Log
import com.example.myapplication.ACTIVITY_CONTEXT
import com.example.myapplication.database.AUTH
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

var storedVerificationId = ""
lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

fun startPhoneNumberVerification(phoneNumber: String) {
    // [START start_phone_auth]
    val options = PhoneAuthOptions.newBuilder(AUTH)
        .setPhoneNumber(phoneNumber)       // Phone number to verify
        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
        .setActivity(ACTIVITY_CONTEXT)                 // Activity (for callback binding)
        .setCallbacks(Callbacks)          // OnVerificationStateChangedCallbacks
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
    // [END start_phone_auth]
}

fun verifyPhoneNumberWithCode(code: String) {
    // [START verify_with_code]
    val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
    signInWithPhoneAuthCredential(credential)
    // [END verify_with_code]
}

// [START sign_in_with_phone]
private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
    AUTH.signInWithCredential(credential)
        .addOnCompleteListener(ACTIVITY_CONTEXT) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithCredential:success")
            } else {
                // Sign in failed, display a message and update the UI
                Log.w(TAG, "signInWithCredential:failure", task.exception)
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                    Log.w(TAG, "signInWithCredential:invalid verification code")
                }
                // Update UI
            }
        }
}
// [END sign_in_with_phone]

object Callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        // This callback will be invoked in two situations:
        // 1 - Instant verification. In some cases the phone number can be instantly
        //     verified without needing to send or enter a verification code.
        // 2 - Auto-retrieval. On some devices Google Play services can automatically
        //     detect the incoming verification SMS and perform verification without
        //     user action.
        Log.d(TAG, "onVerificationCompleted:$credential")
        signInWithPhoneAuthCredential(credential)
    }

    override fun onVerificationFailed(e: FirebaseException) {
        // This callback is invoked in an invalid request for verification is made,
        // for instance if the the phone number format is not valid.
        Log.w(TAG, "onVerificationFailed", e)

        if (e is FirebaseAuthInvalidCredentialsException) {
            // Invalid request
        } else if (e is FirebaseTooManyRequestsException) {
            // The SMS quota for the project has been exceeded
            Log.w(TAG, "SMS quota for the project has been exceeded")
        }

        // Show a message and update the UI
    }

    override fun onCodeSent(
        verificationId: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        // The SMS verification code has been sent to the provided phone number, we
        // now need to ask the user to enter the code and then construct a credential
        // by combining the code with a verification ID.
        Log.d(TAG, "onCodeSent:$verificationId")

        // Save verification ID and resending token so we can use them later
        storedVerificationId = verificationId
        resendToken = token
    }
}