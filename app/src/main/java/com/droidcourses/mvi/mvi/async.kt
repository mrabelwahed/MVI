package com.droidcourses.mvi.mvi


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private  const val BUFFER_SIZE = 64

open class AsyncMviViewModel<S:State, A:Action>(
    private val reducer: Reducer<S,A>,
    private val middlewares:List<Middleware<S,A>> = emptyList(),
    initialState:S

): ViewModel(), Dispatcher<A> {

    private data class ActionImpl<S:State, A:Action>(
        val state: S,
        val action: A
    )

    private val actions = MutableSharedFlow<ActionImpl<S,A>>(extraBufferCapacity = BUFFER_SIZE)

    var state: S by mutableStateOf(initialState)
     private set

    init {
        middlewares.forEach { middleware ->
            middleware.setDispatcher(this)
        }

        viewModelScope.launch {
           actions.onEach { actionImpl ->
               middlewares.forEach { middleware ->  
                   middleware.process(actionImpl.state,actionImpl.action)
               }
           }
               .collect()
        }

        viewModelScope.launch {
            actions.collect {
                state = reducer.reduce(state,it.action)
            }

        }

    }


    override fun dispatch(action: A) {
        val success = actions.tryEmit(ActionImpl(state,action))
        if (!success) error("MVI action buffer overflow")
    }


}