package com.appleobject.leaderboard.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LearnerNetworkEntity(

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("hours")
    @Expose
    var hours: Int,

    @SerializedName("country")
    @Expose
    var country: String,

    @SerializedName("badgeUrl")
    @Expose
    var badgeUrl: String
) {
}