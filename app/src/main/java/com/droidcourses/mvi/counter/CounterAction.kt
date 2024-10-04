package com.droidcourses.mvi.counter

import com.droidcourses.mvi.mvi.Action

sealed interface CounterAction: Action {
    data object Increment: CounterAction
    data object Decrement: CounterAction
}