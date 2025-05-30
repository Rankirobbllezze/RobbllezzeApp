package com.example.robbllezze.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.robbllezze.presentation.components.LoginScreen
import com.example.robbllezze.presentation.components.SignUpScreen
import com.example.robbllezze.presentation.screens.apiscreens.ApiDashboard
import com.example.robbllezze.presentation.screens.dashboard.DashboardScreen

// INSIDE THIS FILE WE WILL DEFINE NAVCONTROLLER : THIS IS USED TO NAVIGATE
// TO DIFFERENT COMPOSABLES / SCREENS
@Composable
fun TodoNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "signUp") {
        composable("signUp"){
            SignUpScreen(navController)
        }
        composable("login"){
            LoginScreen(navController)
        }
        composable("dashboard"){
            DashboardScreen(
                //properties for the composable
                navController
            )

        }
        composable("apiroute"){
            ApiDashboard(
                navController
            )
        }
        // here will define the addtoDo composable

    }

}


