package com.droidcourses.mvi.counter

import com.droidcourses.mvi.mvi.Middleware
import kotlinx.coroutines.delay
import kotlin.random.Random

class CounterMiddleware: Middleware<CounterStateV2, CounterActionV2>() {
    override suspend fun process(state: CounterStateV2, action: CounterActionV2) {
        when (action) {
            CounterActionV2.GenerateRandom -> generateRandom()
            else -> { /* no-op */ }
        }
    }

    private suspend fun generateRandom() {
        // 4
        dispatch(CounterActionV2.Loading)
        // 5
        delay(500L + Random.nextLong(2000L))
        val counterValue = Random.nextInt(100)
        // 6
        dispatch(CounterActionV2.CounterUpdated(counterValue))
    }
}