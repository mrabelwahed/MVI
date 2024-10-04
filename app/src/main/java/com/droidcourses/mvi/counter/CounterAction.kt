package com.droidcourses.mvi.counter

import com.droidcourses.mvi.mvi.Action

sealed interface CounterAction: Action {
    data object Increment: CounterAction
    data object Decrement: CounterAction
}


sealed interface CounterActionV2: Action {
    data object Loading : CounterActionV2
    data object Increment : CounterActionV2
    data object Decrement : CounterActionV2
    data object GenerateRandom : CounterActionV2
    data class CounterUpdated(val value: Int) : CounterActionV2
}