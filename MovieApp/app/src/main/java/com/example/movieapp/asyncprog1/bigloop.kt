package com.example.movieapp.asyncprog1

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun loopWithoutCoroutine(){
    var isLoading by remember { mutableStateOf(false) }
    var data by remember { mutableLongStateOf(0) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Button(onClick = {
                Toast.makeText(context, "Toast",
                    Toast.LENGTH_LONG).show()
            }) {
                Text("Show Toast")
            }
            Button(onClick = {
                isLoading = true
                coroutineScope.launch {
                    data = runLoop(1_000)
                    isLoading = false
                }
            }) {
                Text("Run Loop")
            }
            if(isLoading){
                CircularProgressIndicator()
            }else{
                Text("Result = $data")
            }
        }
    }
}

suspend fun runLoop(max: Long = 1000L): Long{
    var total: Long = 0
    for(index: Long in 1..max){
        total += index
        delay(5) // 1000 means 1 second
    }
    return total
}