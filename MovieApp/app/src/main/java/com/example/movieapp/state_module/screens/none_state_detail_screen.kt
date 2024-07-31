package com.example.movieapp.state_module.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.state_module.viewmodels.CounterViewModel

@Preview(showBackground = true)
@Composable
fun NoneStateDetailScreen(counterVM: CounterViewModel = viewModel()) {

//    var counter by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red.copy(alpha = 0.2f)),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column() {
            Text("Detail Screen")
            Button(onClick = {
                counterVM.decrease()
            }) {
                Text("Decrease")
            }
            Button(onClick = {
                counterVM.increase()
            }) {
                Text("Increase")
            }
            Text("Counter: ${counterVM.counter}")
        }
    }
}