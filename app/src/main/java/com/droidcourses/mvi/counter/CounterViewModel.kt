package com.droidcourses.mvi.counter

import com.droidcourses.mvi.mvi.MviViewModel

class CounterViewModel(reducer: CounterReducer = CounterReducer()):
    MviViewModel<CounterState, CounterAction>(reducer = reducer, initialState = CounterState.initial) {

        fun onIncrement() {
            dispatch(CounterAction.Increment)
        }

        fun onDecrement() {
            dispatch(CounterAction.Decrement)
        }
}