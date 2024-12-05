package com.example.turkiyemcomposee.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.turkiyemcomposee.ui.theme.TurkiyemcomposeeTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun loginscreen(navController: NavController,auth: FirebaseAuth)
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailerror by remember { mutableStateOf(false) }
    var passworderror by remember { mutableStateOf(false) }
    var errormessage by remember { mutableStateOf("") }

    Scaffold { innerpadding->
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7C65A9), // Mor
                            Color(0xFF96D4CA)  // Yeşil
                        )
                    )
                )
                .padding(innerpadding)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).fillMaxSize()) {

            TextField(
                value =  email,
                onValueChange = { email = it
                                  emailerror = false
                                },
                isError = emailerror,
                modifier = Modifier.fillMaxWidth()
                            .padding(12.dp)
                ,placeholder = { Text("Email...") })
            if (emailerror)
            {
                Text("Invalid email address", color = Color.Red)
            }

            OutlinedTextField(
                value =  password,
                onValueChange = {
                password = it
                    passworderror = false
                },
                isError = passworderror,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp)
                    .background(Color(0xFFE0E0E0))
                    .border(1.dp, Color(0xFFBDBDBD))
                ,placeholder = { Text("Password...") }
            )
            if (passworderror)
            {
                Text("Password must be at least 6 characters", color = Color.Red)
            }

            Button(onClick = {
                when{
                    !isValidEmail(email)->{
                        emailerror = true
                    }
                    password.length < 6 ->{
                        passworderror = true
                    }
                    else -> {
                        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                                task->
                            if (task.isSuccessful){
                                errormessage = "Login Successfull"
                                navController.navigate("Citys")
                            }
                            else{
                                task.exception?.localizedMessage ?: "Login failed"
                            }
                        }
                    }
                }

            }, content = { Text("Login") })

            if (errormessage.isEmpty()){
                Text(errormessage, color = Color.Green)
            }
        }
    }

}

fun isValidEmail(email: String): Boolean
{
    val emailregex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(emailregex)
}


/*
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TurkiyemcomposeeTheme {
        loginscreen()
    }
}
*/