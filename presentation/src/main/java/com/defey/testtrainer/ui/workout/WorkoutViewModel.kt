package com.defey.testtrainer.ui.workout

import androidx.lifecycle.ViewModel
import com.defey.testtrainer.base.BaseContract
import com.defey.testtrainer.navigation.AppRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    navArgs: WorkoutNavArgs,
    private val router: AppRouter,
): ViewModel(), BaseContract<WorkoutUiContract.WorkoutState, WorkoutUiContract.WorkoutEvent> {

    private val _state = MutableStateFlow(WorkoutUiContract.WorkoutState())
    override val state: StateFlow<WorkoutUiContract.WorkoutState> = _state.asStateFlow()

    override fun handleEvent(event: WorkoutUiContract.WorkoutEvent) {
       when(event) {
           WorkoutUiContract.WorkoutEvent.OnStartInterval -> {}
           WorkoutUiContract.WorkoutEvent.OnStopInterval -> {}
       }
    }
}