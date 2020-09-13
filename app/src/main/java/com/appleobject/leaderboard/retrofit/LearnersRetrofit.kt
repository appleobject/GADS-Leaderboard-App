package com.appleobject.leaderboard.retrofit

import com.appleobject.leaderboard.model.Learners
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LearnersRetrofit {

    @GET("api/hours")
    fun getLearnersHours(): Call<List<Learners>>

    @GET("api/skilliq")
    fun getLearnersSkills(): Call<List<Skilliq>>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submitApp(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.284483984") linkProject: String
    ): Call<Void>
}