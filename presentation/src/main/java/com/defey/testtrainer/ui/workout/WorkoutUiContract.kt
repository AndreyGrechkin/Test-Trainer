package com.defey.testtrainer.ui.workout

import com.defey.testtrainer.Workout

class WorkoutUiContract {

    data class WorkoutState(
        val name: String = "",
        val workoutInterval: Workout? = null,
        val currentIntervalIndex: Int = 0,
        val isRunning: Boolean = false,
    )

    sealed interface WorkoutEvent {
        data object OnStartInterval : WorkoutEvent
        data object OnStopInterval : WorkoutEvent
    }
}