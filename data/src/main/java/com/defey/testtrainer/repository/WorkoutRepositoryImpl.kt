package com.defey.testtrainer.repository

import com.defey.testtrainer.modelDto.IntervalDto
import com.defey.testtrainer.modelDto.IntervalTimersDto
import com.defey.testtrainer.models.Interval
import com.defey.testtrainer.models.IntervalTimer
import com.defey.testtrainer.network.ApiResponseHandler
import com.defey.testtrainer.serviceApi.WorkoutApi
import com.defey.testtrainer.utils.NetworkResult
import com.defey.testtrainer.utils.mapSuccess
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutRepositoryImpl @Inject constructor(
    val api: WorkoutApi,
    private val responseHandler: ApiResponseHandler,
) : WorkoutRepository {

    override suspend fun getWorkout(id: String): NetworkResult<IntervalTimer> =
        withContext(Dispatchers.IO) {
            return@withContext responseHandler.handleApiCall { api.getWorkout(id) }
                .mapSuccess { it.timer.toIntervalTimer() }
        }

    private fun IntervalTimersDto.toIntervalTimer(): IntervalTimer =
        IntervalTimer(
            timerId = this.timerId,
            title = this.title,
            totalTime = this.totalTime,
            intervals = this.intervals.map { it.toInterval() }
        )

    private fun IntervalDto.toInterval(): Interval =
        Interval(
            title = this.title,
            time = this.time
        )
}