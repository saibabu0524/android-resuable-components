package com.saibabui.spendit.auth.login.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.saibabui.spendit.common.NetworkResult
import kotlinx.coroutines.tasks.await

class FirebaseLoginRepositoryImpl(override val auth: FirebaseAuth) : FirebaseLoginRepository {
    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): NetworkResult<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Log.d("Login Repository", "Login success")
            NetworkResult.Success(true)
        } catch (e: Exception) {
            NetworkResult.Error(e.message, false)
        }
    }
}