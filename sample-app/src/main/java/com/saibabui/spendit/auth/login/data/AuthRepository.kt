package com.saibabui.spendit.auth.login.data

import com.saibabui.spendit.common.NetworkResult

interface AuthRepository {
    suspend fun login()
    suspend fun register(email: String, password: String): NetworkResult<Boolean>
    suspend fun logout()
    suspend fun forgotPassword(email: String): NetworkResult<Boolean>
}