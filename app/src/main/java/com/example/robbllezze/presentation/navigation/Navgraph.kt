package com.example.robbllezze.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.robbllezze.presentation.screens.dashboard.DashboardScreen

//INSIDE THIS FILE WE WILL DEFINE NAVCONTROLLER : THIS IS USED TO NAVIGATE TO DIFFERENT COMPOSABLES/ SCREENS
@Composable
fun TodoNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "Dashboard") {
        composable("Dashboard"){
            DashboardScreen(
                // properties for the composable
            )

        }
        //here will define the addToDo composable
    }

}