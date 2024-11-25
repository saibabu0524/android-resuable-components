package com.saibabui.spendit.common

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(details: T) : NetworkResult<T>(details)
    class Error<T>(message: String?, details: T? = null) : NetworkResult<T>(details, message)
    class Loading<T> : NetworkResult<T>()
}