package com.example.movieapp.state_module.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    var counter by mutableIntStateOf(0)
        private set

    fun increase() {
        counter++
    }

    fun decrease() {
        counter--
    }
}
