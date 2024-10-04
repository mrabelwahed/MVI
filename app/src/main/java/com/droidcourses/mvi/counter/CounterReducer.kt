package com.droidcourses.mvi.counter

import com.droidcourses.mvi.mvi.Reducer

class CounterReducer: Reducer<CounterState, CounterAction> {
    override fun reduce(state: CounterState, action: CounterAction): CounterState {
       return when(action) {
            CounterAction.Increment -> state.copy(counter = state.counter + 1)
            CounterAction.Decrement -> state.copy(counter = state.counter - 1)
        }
    }
}