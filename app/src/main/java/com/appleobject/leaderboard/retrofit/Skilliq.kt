package com.appleobject.leaderboard.retrofit

import com.google.gson.annotations.SerializedName

data class Skilliq(
    @SerializedName("name")
    var name: String,

    @SerializedName("score")
    var score: Int,

    @SerializedName("country")
    var country: String,

    @SerializedName("badgeUrl")
    var badgeUrl: String
) {
}