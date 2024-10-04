package com.droidcourses.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidcourses.mvi.counter.CounterState
import com.droidcourses.mvi.counter.CounterStateV2
import com.droidcourses.mvi.counter.CounterViewModel
import com.droidcourses.mvi.counter.CounterViewModelV2
import com.droidcourses.mvi.ui.theme.MVITheme

class MainActivity : ComponentActivity() {
    private val counterViewModel by viewModels<CounterViewModelV2>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = counterViewModel.state,
                        onIncreaseClick = counterViewModel::onIncrement,
                        onDecreaseClick = counterViewModel::onDecrement,
                        onGenerateRandom = counterViewModel::onGenerateRandom
                    )
                }
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier,
             state: CounterState,
             onIncrement:() -> Unit,
             onDecrement:() -> Unit
) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Row (modifier = modifier){
            FilledIconButton(onClick = onDecrement) {
                Icon(painter = painterResource(R.drawable.baseline_remove_circle_24), "decrement")
            }

            Text(
                text = state.counter.toString(),
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(horizontal = 16.dp)
                )

            Row {
                FilledIconButton(onClick = onIncrement) {
                    Icon(Icons.Default.AddCircle, "add button")
                }
            }
        }
    }
}

@Composable
fun CounterScreen(
    state: CounterStateV2,
    onDecreaseClick: () -> Unit,
    onIncreaseClick: () -> Unit,
    onGenerateRandom: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FilledIconButton(
                    onClick = onDecreaseClick,
                    enabled = !state.loading,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_remove_circle_24) ,
                        contentDescription = "decrement"
                    )
                }
                Text(
                    text = state.counter.toString(),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
                FilledIconButton (
                    onClick = onIncreaseClick,
                    enabled = !state.loading,
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "increment"
                    )
                }
            }
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = onGenerateRandom,
                enabled = !state.loading,
                modifier = Modifier.padding(top = 16.dp),
            ) {
                Text(
                    text = "Generate Random"
                )
            }
        }
        if (state.loading) {
            CircularProgressIndicator()
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MVITheme {
            Counter(modifier = Modifier, state = CounterState(counter = 0), {},{} )
        }
    }
