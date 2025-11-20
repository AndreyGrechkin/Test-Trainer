package com.defey.testtrainer.models

data class IntervalTimer(
    val timerId: Int,
    val title: String,
    val totalTime: Int,
    val intervals: List<Interval>
)
