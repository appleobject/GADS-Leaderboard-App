package com.appleobject.leaderboard.retrofit

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    //BaseUrl
    private const val URL = "https://gadsapi.herokuapp.com"

    //Create a logger
    private val logger = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    //Creating a custom interceptor to apply headers application wide
    private val headerInterceptor = Interceptor{chain ->
        val request = chain.request()
        request.newBuilder()
            .addHeader("x-device-type",Build.DEVICE)
            .build()

        val response = chain.proceed(request)
        response
    }

    //Create okHttp client
    private val okHttp = OkHttpClient.Builder()
        .callTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)

    //Create a retrofit builder
    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //Create a retrofit instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }
}