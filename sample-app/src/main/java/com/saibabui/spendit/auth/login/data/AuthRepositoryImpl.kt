package com.saibabui.spendit.auth.login.data

import com.saibabui.common_coroutine_dispachers.CoroutineDispatcherProvider
import com.saibabui.spendit.common.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : AuthRepository {
    override suspend fun login() {
        CoroutineScope(coroutineDispatcherProvider.io()).launch {

        }
    }

    override suspend fun register(email: String, password: String): NetworkResult<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(email: String): NetworkResult<Boolean> {
        TODO("Not yet implemented")
    }
}