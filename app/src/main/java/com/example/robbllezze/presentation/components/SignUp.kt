package com.example.robbllezze.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SignUpScreen(
    navController: NavController
) {
    // variables to store and reference the inputs
    val context = LocalContext.current // declares the current processing activity
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) } // capture and ref errors

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Sign Up", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            // error is populated on the condition that an error is encountered
            // the error variable will be populated with a text composable
            // showing the error
            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(  onClick  = {
                if (password != confirmPassword) {
                    error = "password does not match!!"
                } else {
                    // register our user to firebase
                    registerUser(email, password,
                        context,navController,
                        onError = {errorMsg  -> error = errorMsg})
                }
            }
            ) {
                Text("Sign Up")
            }

            Spacer(modifier = Modifier.height(8.dp))
//navigate to login //navigate to login
            TextButton(onClick = {  navController.navigate("login") })
            {
                Text("Already have an account? Login")
            }

        }
    }
}

fun registerUser(email: String, password: String,
                 context: Context, navController: NavController,
                 onError: (String)-> Unit ) {
    Firebase.auth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener{ task ->
            if(task.isSuccessful){
                navController.navigate("login")
            } else {
                onError(task.exception?.message ?:
                "Registration failed")
            }
        }
}


@Preview
@Composable
fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen(rememberNavController())
    }
}