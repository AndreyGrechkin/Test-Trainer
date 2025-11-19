package com.defey.testtrainer.navigation

sealed class Screen() {
    object LoadWorkout : Screen()
    data class Workout(val id: String) : Screen()
}