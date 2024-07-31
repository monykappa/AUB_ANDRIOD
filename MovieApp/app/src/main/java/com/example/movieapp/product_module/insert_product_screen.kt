package com.example.movieapp.product_module

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SampleLoginUI(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var hidePassword by remember { mutableStateOf(true) }

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("Enter Email") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                )
            )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                placeholder = { Text("Enter Password") },
                leadingIcon = {
                    Icon(Icons.Default.Key, contentDescription = null)
                },
                visualTransformation = if (hidePassword) PasswordVisualTransformation()
                else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        hidePassword = !hidePassword
                    }) {
                        if (hidePassword) {
                            Icon(Icons.Default.Visibility, contentDescription = null)
                        } else {
                            Icon(Icons.Default.VisibilityOff, contentDescription = null)
                        }

                    }
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                onClick = {
                    if (email == "kosal" && password == "123") {
                        Toast.makeText(
                            context,
                            "Login Success",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Login Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
            ) {
                Text("Login")
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun InsertProductScreen(){

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ){
        Column(
            modifier = Modifier.padding(10.dp),
        ){
            TextField(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                placeholder = { Text(text = "Enter Title") },
                leadingIcon = {
                    Icon(Icons.Default.TextFields, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(5.dp).height(100.dp),
                value = body,
                onValueChange = { body = it },
                label = { Text(text = "Body") },
                placeholder = { Text(text = "Enter Body") },
                leadingIcon = {
                    Icon(Icons.Default.Book, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                value = price,
                onValueChange = { price = it },
                label = { Text( "Price") },
                placeholder = { Text("Enter Price") },
                leadingIcon = {
                    Icon(Icons.Default.PriceChange, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                )
            )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                value = qty,
                onValueChange = { title = qty },
                label = { Text( "Qty") },
                placeholder = { Text("Enter Qty") },
                leadingIcon = {
                    Icon(Icons.Default.Numbers, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                )
            )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                value = category,
                onValueChange = { category = it },
                label = { Text( "Category") },
                placeholder = { Text("Enter Category") },
                leadingIcon = {
                    Icon(Icons.Default.Category, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )
            TextField(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text( "Image Url") },
                placeholder = { Text("Enter Image Url") },
                leadingIcon = {
                    Icon(Icons.Default.Image, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                onClick = {

                }

            ){
                Text("Insert Product")
            }


        }
    }



}