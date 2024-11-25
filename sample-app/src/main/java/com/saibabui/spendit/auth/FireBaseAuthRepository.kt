package com.saibabui.spendit.auth

import com.google.firebase.auth.FirebaseUser
import com.saibabui.spendit.common.NetworkResult

interface FireBaseAuthRepository {

    val user: FirebaseUser?

    suspend fun signUpWithEmailAndPassword(email: String, password: String): NetworkResult<Boolean>

    suspend fun signInWithEmailAndPassword(email: String, password: String): NetworkResult<Boolean>
}