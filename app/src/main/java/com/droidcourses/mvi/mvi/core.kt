package com.droidcourses.mvi.mvi

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

interface State

interface Action

interface Reducer<S:State, A: Action> {
    fun reduce(state:S , action: A): S
}

private const val BUFFER_SIZE = 64

open class MviViewModel<S:State , A: Action>(private val reducer: Reducer<S,A>, initialState: S): ViewModel(){
    private val  actions = MutableSharedFlow<A>(extraBufferCapacity = BUFFER_SIZE)
    var state: S by mutableStateOf(initialState)
        private  set

    init {
        viewModelScope.launch {
            actions.collect { action ->
                state = reducer.reduce(state, action)
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun dispatch(action: A) {
      val success  =actions.tryEmit(action)
        if (!success)
            error("MVI action buffer flow")
    }

}