package com.defey.testtrainer.ui.loadWorkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.defey.testtrainer.base.BaseContract
import com.defey.testtrainer.navigation.AppRouter
import com.defey.testtrainer.navigation.Screen
import com.defey.testtrainer.repository.WorkoutRepository
import com.defey.testtrainer.utils.finally
import com.defey.testtrainer.utils.onError
import com.defey.testtrainer.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadWorkoutViewModel @Inject constructor(
    private val router: AppRouter,
    private val repository: WorkoutRepository
) : ViewModel(), BaseContract<LoadWorkoutUiContract.LoadState, LoadWorkoutUiContract.LoadEvent> {

    private val _state = MutableStateFlow(LoadWorkoutUiContract.LoadState())
    override val state: StateFlow<LoadWorkoutUiContract.LoadState> = _state.asStateFlow()

    override fun handleEvent(event: LoadWorkoutUiContract.LoadEvent) {
        when (event) {
            LoadWorkoutUiContract.LoadEvent.OnLoadWorkout -> loadingWorkout()
            is LoadWorkoutUiContract.LoadEvent.OnIdQueryChanged -> {
                _state.update { it.copy(id = event.id) }
            }
        }
    }

    private fun loadingWorkout() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getWorkout(state.value.id).onSuccess { response ->
                router.navigateTo(Screen.Workout(state.value.id))
            }.onError { message, code ->
            }.finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }


}