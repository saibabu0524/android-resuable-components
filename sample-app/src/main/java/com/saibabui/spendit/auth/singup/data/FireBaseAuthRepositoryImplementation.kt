package com.saibabui.spendit.auth.singup.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.saibabui.spendit.auth.FireBaseAuthRepository
import com.saibabui.spendit.common.NetworkResult
import kotlinx.coroutines.tasks.await

class FireBaseAuthRepositoryImplementation(
    val auth: FirebaseAuth
) : FireBaseAuthRepository {

    override val user: FirebaseUser? = Firebase.auth.currentUser

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): NetworkResult<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Log.d("Firebase", "Signup success")
            NetworkResult.Success(true)
        } catch (e: Exception) {
            NetworkResult.Error("Failed", false)
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): NetworkResult<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Log.d("Firebase", "SingIn success")
            NetworkResult.Success(true)
        } catch (e: Exception) {
            NetworkResult.Error("Failed", false)
        }
    }
}