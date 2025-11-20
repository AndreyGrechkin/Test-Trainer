package com.defey.testtrainer.ui.workout

import com.defey.testtrainer.models.IntervalTimer

class WorkoutUiContract {

    data class WorkoutState(
        val workoutInterval: IntervalTimer? = null,
        val currentIntervalIndex: Int = 0,
        val isRunning: Boolean = false,
        val currentTimeLeft: Int = 0,
    )

    sealed interface WorkoutEvent {
        data object OnStartInterval : WorkoutEvent
        data object OnStopInterval : WorkoutEvent
    }
}