package com.example.turkiyemcomposee.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.turkiyemcomposee.ui.theme.TurkiyemcomposeeTheme
import com.google.firebase.auth.FirebaseAuth


//
@Composable
fun register(navController: NavController,auth: FirebaseAuth){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var emailerror by remember { mutableStateOf(false) }
    var passworderror by remember { mutableStateOf(false) }
    var confirmpassworderror by remember { mutableStateOf(false) }
    var errormessage by remember { mutableStateOf("") }

    Scaffold { innerPadding ->

        // Gradient Background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7C65A9), // Mor
                            Color(0xFF96D4CA)  // Yeşil
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    "Welcome To Our App",
                    modifier = Modifier.padding(vertical = 20.dp),
                    color = Color.White,
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it
                                    emailerror = false},
                    isError = emailerror,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = { Text("E-mail") }
                )
                if (emailerror){
                    Text("Invalid email address", color = Color.Red)
                }

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it
                                    passworderror = false},
                    isError = false,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE0E0E0))
                        .border(1.dp, Color(0xFFBDBDBD))
                        .padding(12.dp),
                    placeholder = { Text("Password") }
                )
                if(passworderror){
                    Text("Invalid password", color = Color.Red)
                }

                OutlinedTextField(
                    value = confirmpassword,
                    onValueChange = { confirmpassword = it
                                    confirmpassworderror = false},
                    isError = false,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE0E0E0))
                        .border(1.dp, Color(0xFFBDBDBD))
                        .padding(12.dp),
                    placeholder = { Text("Confirm Password") }
                )
                if (confirmpassworderror){
                    Text("Invalid confirm password", color = Color.Red)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        when{
                            !isValidEmail(email)->{
                                emailerror = true
                            }
                            password.length < 6 ->{
                                passworderror = true
                            }
                            password != confirmpassword ->{
                                confirmpassworderror = true
                            }
                            else -> {
                                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                                 task ->
                                    if(task.isSuccessful){
                                        navController.navigate("Citys")
                                    }
                                    else{
                                        errormessage = task.exception?.localizedMessage ?: "Register Failed"
                                    }
                                }
                            }
                        }

                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Sign Up")
                }

                if (errormessage.isEmpty()){
                    Text(errormessage, color = Color.Green)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "Already have an account? Login",
                    color = Color.White,
                    modifier = Modifier.clickable {
                        navController.navigate("Login")
                    }
                )
            }
        }
    }

}



/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TurkiyemcomposeeTheme {
                register()
        }
    }


 */