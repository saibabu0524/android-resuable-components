package com.saibabui.common_coroutine_dispachers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineDispatcherProvider {
    fun io() : CoroutineDispatcher = Dispatchers.IO
    fun main() : CoroutineDispatcher = Dispatchers.Main
    fun default() : CoroutineDispatcher = Dispatchers.Default
    fun unconfined() : CoroutineDispatcher = Dispatchers.Unconfined
}

class DefaultCoroutineDispatcherProvider : CoroutineDispatcherProvider