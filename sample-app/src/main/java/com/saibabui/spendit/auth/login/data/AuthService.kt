package com.saibabui.spendit.auth.login.data

import com.saibabui.spendit.common.NetworkResult
import retrofit2.http.POST

interface AuthService {
    @POST("/login")
    fun login(email: String, password: String): NetworkResult<Boolean>

    @POST("/register")
    fun register(email: String, password: String): NetworkResult<Boolean>

    @POST("/logout")
    fun logout()

    @POST("/forgot_password")
    fun forgotPassword(email: String): NetworkResult<Boolean>
}