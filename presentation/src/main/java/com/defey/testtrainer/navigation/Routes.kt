package com.defey.testtrainer.navigation

import com.defey.testtrainer.ui.workout.WorkoutNavArgs

object Routes {
    const val LOADING_WORKOUT = "loading_workout"
    const val WORKOUT = "workout/{${WorkoutNavArgs.WORKOUT_ID_ARG}}"

    fun getWorkoutRoute(id: String): String {
        return "workout/$id"
    }
}