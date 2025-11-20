package com.defey.testtrainer.serviceApi

import com.defey.testtrainer.modelDto.IntervalTimersResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WorkoutApi {

    @GET("/api/v2/interval-timers/{id}")
    suspend fun getWorkout(
        @Path("id") id: String,
    ): Response<IntervalTimersResponseDto>
}