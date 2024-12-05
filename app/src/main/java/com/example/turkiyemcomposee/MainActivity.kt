package com.example.turkiyemcomposee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.turkiyemcomposee.screens.register
import com.example.turkiyemcomposee.ui.theme.TurkiyemcomposeeTheme
import com.example.turkiyemcomposee.viewmodel.viewmodel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    val viewmodell : viewmodel by viewModels<viewmodel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        setContent {
            TurkiyemcomposeeTheme {
                navigate(viewmodell,auth)
            }
            }
        }
    }

@Composable
fun lineEdit()
{
    var text by remember { mutableStateOf("") }
    TextField(value = "",onValueChange = {}, placeholder = { Text("Adınız") })
}

@Composable
fun SimpleOutlinedTextFieldSample(placeholdername: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text(placeholdername) },
        visualTransformation = PasswordVisualTransformation()
    )
}
/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TurkiyemcomposeeTheme {
        Scaffold(
            modifier = Modifier.size(width = 412.dp, height = 915.dp), containerColor = Color.LightGray
        ) { innerpadding ->
            Column(modifier = Modifier.padding(innerpadding)) {
                titleText()
                register()
                login()
            }

        }
    }
}
*/
