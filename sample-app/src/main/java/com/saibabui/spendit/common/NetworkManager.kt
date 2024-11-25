package com.saibabui.spendit.common

import android.annotation.SuppressLint
import android.content.Context
import com.saibabui.spendit.common.Constants.BASE_URL
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@SuppressLint("StaticFieldLeak")
object NetworkManager {


    private const val CONNECT_TIMEOUT = 60L
    private const val READ_TIMEOUT = CONNECT_TIMEOUT
    private const val WRITE_TIMEOUT = CONNECT_TIMEOUT
    private const val API_TOKEN =" "
    inline fun <reified T> apiService(): T = retrofitInstance.create(T::class.java)
    private lateinit var context: Context


    val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(getProdClient())
            .build()
    }

    private fun getProdClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $API_TOKEN")
                .addHeader("content-type", "application/json")
            chain.proceed(request.build())
        }

        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClient.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

        httpClient.addInterceptor(getLogInterceptor())
        httpClient.addInterceptor(interceptor)
            .cache(Cache(context.cacheDir, 1024 * 1024 * 10))
        return httpClient.build()
    }

    private fun getLogInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
        return logging
    }
}