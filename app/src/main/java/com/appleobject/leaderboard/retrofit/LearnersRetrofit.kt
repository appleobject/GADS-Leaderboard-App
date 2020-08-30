package com.appleobject.leaderboard.retrofit

import com.appleobject.leaderboard.model.Learners
import retrofit2.Call
import retrofit2.http.GET

interface LearnersRetrofit {

    @GET("api/hours")
    fun get(): Call<List<Learners>>
}