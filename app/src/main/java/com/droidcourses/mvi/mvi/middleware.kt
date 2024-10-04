package com.droidcourses.mvi.mvi

interface Dispatcher<A:Action> {
    fun dispatch(action: A)
}

abstract class Middleware<S: State, A:Action> () {
    private lateinit var dispatcher: Dispatcher<A>

    abstract suspend fun process(state: S, action: A)

    internal fun setDispatcher(dispatcher: Dispatcher<A>) {
        this.dispatcher = dispatcher
    }

    protected fun dispatch(action: A) {
        dispatcher.dispatch(action)
    }

}