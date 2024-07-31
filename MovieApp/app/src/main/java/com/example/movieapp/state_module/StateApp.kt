package com.example.movieapp.state_module

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.state_module.screens.NoneStateDetailScreen
import com.example.movieapp.state_module.screens.NoneStateHomeScreen
import com.example.movieapp.state_module.viewmodels.CounterViewModel


@Preview(showSystemUi = true)
@Composable
fun StateApp(){
    val counterVM: CounterViewModel = viewModel()
    Column {
        NoneStateHomeScreen()
        NoneStateDetailScreen()
    }
}