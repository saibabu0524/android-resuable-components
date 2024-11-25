package com.saibabui.spendit.auth.login.data

import com.google.firebase.auth.FirebaseAuth
import com.saibabui.spendit.common.NetworkResult

interface LoginRepository {

    val auth: FirebaseAuth

    suspend fun loginWithEmailAndPassword(email: String, password: String): NetworkResult<Boolean>

}