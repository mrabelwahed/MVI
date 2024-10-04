package com.droidcourses.mvi.counter

import com.droidcourses.mvi.mvi.State

data class CounterState (val counter: Int): State {
    companion object {
        val initial: CounterState = CounterState(counter = 0)
    }
}