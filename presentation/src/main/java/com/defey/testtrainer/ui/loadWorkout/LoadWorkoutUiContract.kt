package com.defey.testtrainer.ui.loadWorkout

class LoadWorkoutUiContract {

    data class LoadState(
        val id: String = "68",
        val isLoading: Boolean = false,
    )

    sealed interface LoadEvent {

        data object OnLoadWorkout : LoadEvent
        data class OnIdQueryChanged(val id: String) : LoadEvent
    }
}