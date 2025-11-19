package com.defey.testtrainer.ui.workout

import androidx.lifecycle.SavedStateHandle
import javax.inject.Inject

class WorkoutNavArgs @Inject constructor(private val savedStateHandle: SavedStateHandle) {

    val workoutId: String
        get() = savedStateHandle.get<String>(WORKOUT_ID_ARG)
            ?: throw IllegalArgumentException("workoutId is required")

    companion object {
        const val WORKOUT_ID_ARG = "workoutId"
    }
}