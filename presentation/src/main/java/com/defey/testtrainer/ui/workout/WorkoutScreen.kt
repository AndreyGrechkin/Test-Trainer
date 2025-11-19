package com.defey.testtrainer.ui.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.defey.testtrainer.Interval
import kotlin.collections.forEachIndexed

@Composable
fun WorkoutScreen(
    viewModel: WorkoutViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    val tabs = listOf("Таймер", "Карта")
    var selectedTab by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = selectedTab == index, onClick = { selectedTab = index }) {
                    Text(title, modifier = Modifier.padding(16.dp))
                }
            }
        }

        when (selectedTab) {
            0 -> TimerTab(viewModel, state)
            1 -> MapTab(viewModel)
        }
    }
}

@Composable
fun TimerTab(viewModel: WorkoutViewModel, state: WorkoutUiContract.WorkoutState) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        workoutIntervalsBars(
            state.workoutInterval?.intervals ?: emptyList(),
            state.currentIntervalIndex
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Общая длительность: ${state.workoutInterval?.totalDuration ?: 0} сек.")

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (state.isRunning)
                viewModel.handleEvent(WorkoutUiContract.WorkoutEvent.OnStopInterval)
            else viewModel.handleEvent(WorkoutUiContract.WorkoutEvent.OnStartInterval)
        }) {
            Text(if (state.isRunning) "Стоп" else "Старт")
        }
    }
}

@Composable
fun workoutIntervalsBars(intervals: List<Interval>, currentIndex: Int) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        intervals.forEachIndexed { index, interval ->
            val weight = interval.durationSeconds.toFloat()
            val color = if (index == currentIndex) Color.Green else Color.Gray
            Box(
                modifier = Modifier
                    .weight(weight)
                    .height(24.dp)
                    .background(color)
            )
        }
    }
}

@Composable
fun MapTab(viewModel: WorkoutViewModel) {
    // Здесь нужно интегрировать Compose карту (Google Maps или Mapbox)
    // и рисовать трек viewModel.gpsTrack
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Здесь будет карта с GPS треком")
    }
}