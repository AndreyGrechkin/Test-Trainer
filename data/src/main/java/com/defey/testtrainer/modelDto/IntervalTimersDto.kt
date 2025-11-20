package com.defey.testtrainer.modelDto

import com.google.gson.annotations.SerializedName

data class IntervalTimersDto(
    @SerializedName("timer_id")
    val timerId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_time")
    val totalTime: Int,
    @SerializedName("intervals")
    val intervals: List<IntervalDto>
)
