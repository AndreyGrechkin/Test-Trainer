package com.defey.testtrainer.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.defey.testtrainer.base.BaseContract
import com.defey.testtrainer.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val navArgs: WorkoutNavArgs,
    private val repository: WorkoutRepository,
    private val soundPlayer: SoundPlayer,
    // TODO: добавить FusedLocationProviderClient для GPS
    // TODO: добавить SavedStateHandle для сохранения состояния таймера и трека при сворачивании/выходе
) : ViewModel(), BaseContract<WorkoutUiContract.WorkoutState, WorkoutUiContract.WorkoutEvent> {

    private val _state = MutableStateFlow(WorkoutUiContract.WorkoutState())
    override val state: StateFlow<WorkoutUiContract.WorkoutState> = _state.asStateFlow()

    // TODO: добавить StateFlow<List<LatLng>> для gpsTrack
    // TODO: добавить LocationCallback для обновления трека
    private var timerJob: Job? = null

    init {
        loadIntervals()
        // TODO: дополнительно загрузить или восстановить состояние таймера и трека из SavedStateHandle
    }

    override fun handleEvent(event: WorkoutUiContract.WorkoutEvent) {
        when (event) {
            WorkoutUiContract.WorkoutEvent.OnStartInterval -> {
                startTimer()
                // TODO: запустить получение GPS координат и запись трека
            }

            WorkoutUiContract.WorkoutEvent.OnStopInterval -> {
                stopTimer()
                // TODO: остановить получение GPS координат
            }
        }
    }

    private fun loadIntervals() {
        val interval = repository.getWorkoutCached(navArgs.workoutId)
        _state.update {
            it.copy(
                workoutInterval = interval,
                currentTimeLeft = interval?.intervals?.firstOrNull()?.time ?: 0
            )
        }
        // TODO: обработать ошибку при загрузке (например, поставить флаг ошибки)
    }

    // TODO: 'сгенерирован с помощью AI вынести из ViewModel'
    private fun startTimer() {
        if (timerJob != null) return
        val workout = _state.value.workoutInterval ?: return
        _state.update { it.copy(isRunning = true) }

        soundPlayer.playSingleBeep()

        timerJob = viewModelScope.launch {
            var index = _state.value.currentIntervalIndex
            while (index < workout.intervals.size) {
                var timeLeft = if (index == _state.value.currentIntervalIndex) {
                    _state.update {
                        it.copy(currentTimeLeft = state.value.currentTimeLeft)
                    }
                    state.value.currentTimeLeft
                } else workout.intervals[index].time
                soundPlayer.playSingleBeep()

                while (timeLeft > 0 && _state.value.isRunning) {
                    delay(1000L)
                    timeLeft -= 1

                    _state.update {
                        it.copy(
                            currentIntervalIndex = index,
                            currentTimeLeft = timeLeft
                        )
                    }
                }
                // TODO: сохранить текущее состояние таймера (currentIntervalIndex, currentTimeLeft) в SavedStateHandle или DataStore
                if (!_state.value.isRunning) break
                index++
            }

            if (index >= workout.intervals.size) {
                soundPlayer.playDoubleBeep()
                stopTimer()
                _state.update { it.copy(currentIntervalIndex = workout.intervals.size - 1) }
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        _state.update {
            it.copy(
                isRunning = false,
                currentIntervalIndex = 0,
                currentTimeLeft = state.value.workoutInterval?.intervals?.firstOrNull()?.time ?: 0
            )
        }
        // TODO: сохранить состояние остановки таймера
    }

    // TODO: методы startLocationUpdates(), stopLocationUpdates() для GPS с использованием LocationServices/FusedLocationProviderClient
}