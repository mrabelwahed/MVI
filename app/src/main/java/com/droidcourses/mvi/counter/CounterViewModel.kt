package com.droidcourses.mvi.counter

import com.droidcourses.mvi.mvi.AsyncMviViewModel
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

class CounterViewModelV2(
    reducer: CounterReducerV2 = CounterReducerV2(),
    middleware: CounterMiddleware = CounterMiddleware()
    ):
    AsyncMviViewModel<CounterStateV2, CounterActionV2>(
        reducer = reducer,
        middlewares = listOf(middleware),
        initialState = CounterStateV2.initial
    ) {

    fun onIncrement() {
        dispatch(CounterActionV2.Increment)
    }

    fun onDecrement() {
        dispatch(CounterActionV2.Decrement)
    }

    fun onGenerateRandom() {
        dispatch(CounterActionV2.GenerateRandom)
    }
}