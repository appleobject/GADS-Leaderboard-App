package com.appleobject.leaderboard.retrofit

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object SubmissionBuilder {

    private const val baseUrl = "https://docs.google.com/forms/d/e/"

    //Create a logger
    private val logger = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create a custom interceptor
    private val interceptor = Interceptor{chain ->
        val request = chain.request()
        request.newBuilder()
            .addHeader("x-device-type", Build.DEVICE)
            .build()

        val response = chain.proceed(request)
        response
    }

    // create OkHttp Client builder
    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(logger)

    // Create a Retrofit builder
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())

    // Create a Retrofit instance
    private val retrofit = retrofitBuilder.build()


    fun <T> buildSubmission(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}