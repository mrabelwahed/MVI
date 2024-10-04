package com.droidcourses.mvi.counter

import com.droidcourses.mvi.mvi.State

data class CounterState (val counter: Int): State {
    companion object {
        val initial: CounterState = CounterState(counter = 0)
    }
}

data class CounterStateV2 (val counter: Int, val loading: Boolean): State {
    companion object {
        val initial: CounterStateV2 = CounterStateV2(counter = 0, loading = false)
    }
}