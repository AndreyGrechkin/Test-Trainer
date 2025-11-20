package com.defey.testtrainer.modelDto

import com.google.gson.annotations.SerializedName

data class IntervalTimersResponseDto(
    @SerializedName("timer")
    val timer: IntervalTimersDto
)
