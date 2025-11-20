package com.defey.testtrainer.repository

import com.defey.testtrainer.models.IntervalTimer
import com.defey.testtrainer.utils.NetworkResult

interface WorkoutRepository {

    suspend fun getWorkout(id: String): NetworkResult<IntervalTimer>
}