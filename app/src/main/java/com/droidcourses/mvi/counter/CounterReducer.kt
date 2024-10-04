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

class CounterReducerV2: Reducer<CounterStateV2, CounterActionV2> {
    override fun reduce(state: CounterStateV2, action: CounterActionV2): CounterStateV2 {
        return when(action) {
            CounterActionV2.Loading -> state.copy(loading = true)
            CounterActionV2.Decrement -> state.copy(counter = state.counter - 1)
            CounterActionV2.Increment -> state.copy(counter = state.counter + 1)
            is CounterActionV2.CounterUpdated -> state.copy(
                loading = false,
                counter = action.value,
            )
            CounterActionV2.GenerateRandom -> state
        }
    }
}