package com.saibabui.spendit.di

import com.saibabui.common_coroutine_dispachers.CoroutineDispatcherProvider
import com.saibabui.common_coroutine_dispachers.DefaultCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule{

    @Provides
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider =
        DefaultCoroutineDispatcherProvider()


}