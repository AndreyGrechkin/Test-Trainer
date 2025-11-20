package com.defey.testtrainer.modelDto

import com.google.gson.annotations.SerializedName

data class IntervalDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("time")
    val time: Int
)