package com.defey.testtrainer.base

import kotlinx.coroutines.flow.StateFlow

interface BaseContract<State : Any, Event : Any> {
    val state: StateFlow<State>
    fun handleEvent(event: Event)
}